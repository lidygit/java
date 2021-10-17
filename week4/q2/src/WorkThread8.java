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
        System.out.println("子线程" + currentThreadName+"计算中。。。");

        return sum();
    }
    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
