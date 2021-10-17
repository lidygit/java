
/**
 * 使用共享变量通信
 */
public class Solution1 {
    public volatile static Integer res = -1;
    public static void main(String[] args) {

        Thread thread = new Thread(new WorkThread1());
        long start=System.currentTimeMillis();
        thread.start();
        while(res==-1){

        }
        System.out.println("异步计算结果为："+res);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        System.out.println("主线程退出。。。");
    }
}
