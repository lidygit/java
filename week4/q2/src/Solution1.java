
/**
 * 使用共享变量通信
 */
public class Solution1 {
    public volatile static boolean flag = true;
    public static void main(String[] args) {

        Thread thread = new Thread(new WorkThread1());
        thread.start();
        while(flag){

        }
        System.out.println("主线程退出。。。");
    }
}
