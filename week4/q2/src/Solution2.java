import java.util.concurrent.CountDownLatch;

/**
 * 使用CountDownLatch
 */
public class Solution2 {
    public static CountDownLatch countDownLatch=new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WorkThread2());
        thread.start();
        countDownLatch.await();
        System.out.println("主线程退出。。。");
    }
}
