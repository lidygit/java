import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用带返回值的子线程
 */
public class Solution8 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTask futureTask = new FutureTask<>(new WorkThread8());
        long start=System.currentTimeMillis();
        futureTask.run();

        while (futureTask.get()==null){

        }
        System.out.println("异步计算结果为："+futureTask.get());

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        System.out.println("主线程退出。。。");
    }
}
