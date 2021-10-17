
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用消息队列
 */
public class Solution6 {
    public static BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(1);
    public static void main(String[] args) throws InterruptedException {
        
        Thread thread = new Thread(new WorkThread6());
        long start=System.currentTimeMillis();
        thread.start();
        Integer res=queue.take();
        System.out.println("异步计算结果为："+res);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        System.out.println("主线程退出。。。");
    }
}
