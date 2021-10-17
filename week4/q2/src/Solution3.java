import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 使用CyclicBarrier
 */
public class Solution3 {
    public static CyclicBarrier cyclicBarrier=new CyclicBarrier(2);
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        Thread thread = new Thread(new WorkThread3());
        thread.start();
        cyclicBarrier.await();
        System.out.println("主线程退出。。。");
    }
}
