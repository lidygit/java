package client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import org.apache.http.HttpVersion;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/10 20:43
 * @需求:
 * @思路说明:
 */
public class NettyHttpClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        FullHttpRequest request = sendHttpRequest();

        ctx.writeAndFlush(request);
    }

    private FullHttpRequest sendHttpRequest() throws URISyntaxException {
        //发送请求至服务端

        URI url = new URI("");
        String msg = "";

        //配置HttpRequest的请求数据和一些配置信息
        FullHttpRequest request = new DefaultFullHttpRequest(HTTP_1_1,
                HttpMethod.GET,
                url.toASCIIString(),
                Unpooled.wrappedBuffer(msg.getBytes(StandardCharsets.UTF_8)));

        request.headers()
                .set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8")
                //开启长连接
                .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
                //设置传递请求内容的长度
                .set(HttpHeaderNames.CONTENT_LENGTH,request.content().readableBytes());
        return request;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpResponse response = (FullHttpResponse) msg;
        ByteBuf content = response.content();
        HttpHeaders headers = response.headers();
        System.out.println("----------content:"+content.toString(StandardCharsets.UTF_8));
        System.out.println("----------headers:"+headers.get("content-type").toString());

        NettyClient.closeServer();
//
//        Thread.sleep(2000);
//        System.out.println(new Date()+"======== 发送请求 =========");
//        FullHttpRequest request = sendHttpRequest();
//        ctx.writeAndFlush(request);
    }
}
