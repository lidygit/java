package gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/10 19:16
 * @需求:
 * @思路说明:
 */
public interface HttpRequestFilter {

    boolean filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);

}
