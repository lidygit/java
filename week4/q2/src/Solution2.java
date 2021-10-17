import java.util.concurrent.CountDownLatch;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/17 19:53
 * @需求:
 * @思路说明:
 */
public class Solution2 {
    public static CountDownLatch countDownLatch=new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new WorkThread());
        thread.start();
        countDownLatch.await();
        System.out.println("主线程退出。。。");
    }
}
