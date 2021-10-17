
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Conditiond的await/signal
 */
public class Solution5 {
    public static ReentrantLock lock=new ReentrantLock();
    public static Condition condition=lock.newCondition();
    public static volatile Integer res=0;
    public static void main(String[] args) throws InterruptedException {

        WorkThread5 workThread5 = new WorkThread5();
        Thread thread = new Thread(workThread5);
        long start=System.currentTimeMillis();
        thread.start();

        try{
            lock.lock();
            condition.await();
        }finally {
            lock.unlock();
        }

        System.out.println("异步计算结果为："+res);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        System.out.println("主线程退出。。。");
    }
}
