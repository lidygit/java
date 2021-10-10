package gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/10 20:01
 * @需求:
 * @思路说明:
 */
public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response, String origin ) {
        response.headers().set("service-provider",origin);
        System.out.println("------- 响应过滤 ------");

    }
}
