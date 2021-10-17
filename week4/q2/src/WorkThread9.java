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
        System.out.println("这是子线程的名称：" + currentThreadName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread);
    }
}
