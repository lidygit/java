import java.util.concurrent.CountDownLatch;

/**
 * 使用CountDownLatch
 */
public class Solution2 {
    public static volatile Integer res=0;
    public static CountDownLatch countDownLatch=new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new WorkThread2());
        long start=System.currentTimeMillis();
        thread.start();
        countDownLatch.await();
        System.out.println("异步计算结果为："+res);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        System.out.println("主线程退出。。。");
    }
}
