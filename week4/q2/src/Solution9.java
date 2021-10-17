import java.util.concurrent.locks.LockSupport;

/**
 * 使用LockSupport
 */
public class Solution9 {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new WorkThread9(Thread.currentThread()));
        thread.start();
        LockSupport.park();
        System.out.println("主线程退出。。。");
    }
}
