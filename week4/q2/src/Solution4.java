/**
 * 使用wait/notify
 */
public class Solution4 {
    public static void main(String[] args) throws InterruptedException {

        WorkThread4 workThread4 = new WorkThread4();
        Thread thread = new Thread(workThread4);
        thread.start();
        synchronized (workThread4){
            workThread4.wait();
        }
        System.out.println("主线程退出。。。");
    }
}
