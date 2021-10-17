import java.util.concurrent.Callable;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/17 22:17
 * @需求:
 * @思路说明:
 */
public class WorkThread8 implements Callable {

    @Override
    public Object call() throws Exception {
        Thread currentThread = Thread.currentThread();
        String currentThreadName = currentThread.getName();
        System.out.println("这是子线程的名称：" + currentThreadName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
