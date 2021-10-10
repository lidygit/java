package gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/10 19:18
 * @需求:
 * @思路说明:
 */
public class HeaderHttpRequestFilter implements HttpRequestFilter {
    @Override
    public boolean filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("inbound","netty-gateway");
        System.out.println("===== 请求过滤header ====");
        return false;
    }
}
