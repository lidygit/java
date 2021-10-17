import java.util.concurrent.locks.LockSupport;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/17 22:39
 * @需求:
 * @思路说明:
 */
public class WorkThread9 implements Runnable {
    Thread thread;


    public WorkThread9(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        String currentThreadName = currentThread.getName();
        System.out.println("子线程" + currentThreadName+"计算中。。。");
        Solution9.res=sum();
        LockSupport.unpark(thread);
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
