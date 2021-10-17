import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/17 19:56
 * @需求:
 * @思路说明:
 */
public class Solution3 {
    public static CyclicBarrier cyclicBarrier=new CyclicBarrier(2);
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        Thread thread = new Thread(new WorkThread());
        thread.start();
        cyclicBarrier.await();
        System.out.println("主线程退出。。。");
    }
}
