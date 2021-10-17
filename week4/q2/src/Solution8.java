import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用带返回值的子线程
 */
public class Solution8 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTask futureTask = new FutureTask<>(new WorkThread8());
        futureTask.run();

        while (!"ok".equals(futureTask.get())){

        }
        System.out.println("主线程退出。。。");
    }
}
