import java.util.concurrent.ThreadFactory;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/17 19:42
 * @需求:
 * @思路说明:
 */
public class Solution1 {
    public volatile static boolean flag = true;
    public static void main(String[] args) {

        Thread thread = new Thread(new WorkThread());
        thread.start();
        while(flag){

        }
        System.out.println("主线程退出。。。");
    }
}
