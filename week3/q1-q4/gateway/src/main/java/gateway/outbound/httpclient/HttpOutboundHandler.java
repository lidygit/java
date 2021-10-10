package gateway.outbound.httpclient;

import gateway.filter.*;
import gateway.router.HttpEndpointRouter;
import gateway.router.RoundRibbonHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/10 19:12
 * @需求:
 * @思路说明:
 */
public class HttpOutboundHandler {

    CloseableHttpAsyncClient httpClient;
    private List<String> backendUrls;

    private ExecutorService proxyService;

    HttpEndpointRouter router = new RoundRibbonHttpEndpointRouter();

    HttpResponseFilter filter = new HeaderHttpResponseFilter();

    public HttpOutboundHandler(List<String> backendUrls) {
        this.backendUrls = backendUrls;

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"), handler);

        //
        IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();

        httpClient = HttpAsyncClients.custom().setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioConfig)
                .setKeepAliveStrategy((response,context) -> 6000)
                .build();
        httpClient.start();
    }



    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        // 路由
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();

        //入站过滤
        filter.filter(fullRequest, ctx);

//        proxyService.submit(()->fetchGet(fullRequest, ctx, url));
        fetchGet(fullRequest, ctx, url);
    }

    private void fetchGet(FullHttpRequest fullRequest, ChannelHandlerContext ctx, String url) {
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpGet.setHeader("inbound",fullRequest.headers().get("inbound"));

        try {
            httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(HttpResponse httpResponse) {
                    handleResponse(fullRequest, ctx, httpResponse);
                    
                }

                @Override
                public void failed(Exception e) {
                    httpGet.abort();
                    e.printStackTrace();
                }

                @Override
                public void cancelled() {
                    httpGet.abort();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(FullHttpRequest fullRequest, ChannelHandlerContext ctx, HttpResponse httpResponse) {

        FullHttpResponse response = null;
        try {
            byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());

            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));

            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(httpResponse.getFirstHeader("Content-Length").getValue()));

            // 出站过滤
            filter.filter(response, "http");
        } catch (IOException e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        }finally {
            if(fullRequest!=null){
                if(!HttpUtil.isKeepAlive(fullRequest)){
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                }else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }

    }
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
