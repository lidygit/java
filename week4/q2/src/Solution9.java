import java.util.concurrent.locks.LockSupport;

/**
 * 使用LockSupport
 */
public class Solution9 {

    public static volatile Integer res=0;
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new WorkThread9(Thread.currentThread()));
        long start=System.currentTimeMillis();
        thread.start();
        LockSupport.park();
        System.out.println("异步计算结果为："+res);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        System.out.println("主线程退出。。。");
    }
}
