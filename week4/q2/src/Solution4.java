/**
 * 使用wait/notify
 */
public class Solution4 {
    public static volatile Integer res=0;
    public static void main(String[] args) throws InterruptedException {

        WorkThread4 workThread4 = new WorkThread4();
        Thread thread = new Thread(workThread4);
        long start=System.currentTimeMillis();
        thread.start();
        synchronized (workThread4){
            workThread4.wait();
        }
        System.out.println("异步计算结果为："+res);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        System.out.println("主线程退出。。。");
    }
}
