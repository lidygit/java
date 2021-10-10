package gateway.router;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/10 19:23
 * @需求:
 * @思路说明:
 */
public interface HttpEndpointRouter {

    String route(List<String> urls);
}
