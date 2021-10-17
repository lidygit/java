/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/17 21:52
 * @需求:
 * @思路说明:
 */
public class WorkThread5 implements Runnable {
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
        try{
            Solution5.lock.lock();
            Solution5.condition.signalAll();
        }finally {
            Solution5.lock.unlock();
        }
    }
}
