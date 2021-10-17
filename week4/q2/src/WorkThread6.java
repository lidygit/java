/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/17 22:00
 * @需求:
 * @思路说明:
 */
public class WorkThread6 implements Runnable {
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
        Solution6.queue.offer(1);
    }
}
