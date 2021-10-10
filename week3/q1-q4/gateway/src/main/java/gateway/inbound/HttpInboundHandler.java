package gateway.inbound;

import client.HttpClient;
import gateway.filter.HeaderHttpRequestFilter;
import gateway.filter.HttpRequestFilter;
import gateway.outbound.httpclient.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/10 18:23
 * @需求:
 * @思路说明:
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private final List<String> proxyServer;

    private HttpOutboundHandler handler;

    private HttpRequestFilter filter = new HeaderHttpRequestFilter();

    public HttpInboundHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
        this.handler = new HttpOutboundHandler(this.proxyServer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request = (FullHttpRequest)msg;
        handler.handle(request,ctx,filter);
    }
}
