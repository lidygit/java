package gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/10 20:01
 * @需求:
 * @思路说明:
 */
public interface HttpResponseFilter {
    void filter(FullHttpResponse response, String origin);
}
