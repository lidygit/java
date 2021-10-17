import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore
 */
public class Solution7 {

    public static Semaphore semaphore=new Semaphore(0);
    public static volatile Integer res=0;
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new WorkThread7());
        long start=System.currentTimeMillis();
        thread.start();
        semaphore.acquire();
        System.out.println("异步计算结果为："+res);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        System.out.println("主线程退出。。。");
    }
}
