
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Conditiond的await/signal
 */
public class Solution5 {
    public static ReentrantLock lock=new ReentrantLock();
    public static Condition condition=lock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        WorkThread5 workThread5 = new WorkThread5();
        Thread thread = new Thread(workThread5);
        thread.start();

        try{
            lock.lock();
            condition.await();
        }finally {
            lock.unlock();
        }

        System.out.println("主线程退出。。。");
    }
}
