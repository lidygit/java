import java.util.concurrent.BrokenBarrierException;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/17 19:43
 * @需求:
 * @思路说明:
 */
public class WorkThread4 implements Runnable  {

    @Override
    public synchronized void run() {

        Thread currentThread = Thread.currentThread();
        String currentThreadName = currentThread.getName();
        System.out.println("子线程" + currentThreadName+"计算中。。。");
        Solution4.res=sum();
        this.notifyAll();
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
