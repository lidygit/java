package gateway.router;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询
 */
public class RoundRibbonHttpEndpointRouter implements HttpEndpointRouter {
    AtomicInteger count= new AtomicInteger(0);
    @Override
    public String route(List<String> urls) {
        int i = count.incrementAndGet();
        int size = urls.size();
        System.out.println("轮询访问："+urls.get(i%size));
        return urls.get(i%size);
    }
}
