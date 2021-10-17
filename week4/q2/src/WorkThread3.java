import java.util.concurrent.BrokenBarrierException;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/17 21:48
 * @需求:
 * @思路说明:
 */
public class WorkThread3 implements Runnable {


    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        String currentThreadName = currentThread.getName();
        System.out.println("子线程" + currentThreadName+"计算中。。。");
        Solution3.res=sum();
        try {
            Solution3.cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
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
