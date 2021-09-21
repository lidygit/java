import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义一个 Classloader，加载一个 Hello.xlass 文件，
 * 执行 hello 方法，
 * 此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。
 */
public class MyClassLoader extends ClassLoader {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Object o = myClassLoader.findClass("Hello").newInstance();
        Method hello = o.getClass().getDeclaredMethod("hello");
        hello.invoke(o);
    }

    @Override
    protected Class<?> findClass(String name) {

        File file = new File(System.getProperty("user.dir")+"/src/Hello.xlass");

        FileInputStream in = null;
        byte[] bytes = null;
        try {
            in = new FileInputStream(file);

            bytes = new byte[in.available()];
            in.read(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return defineClass(name,decode(bytes),0,bytes.length);
    }

    private byte[] decode(byte[] bytes){
        for (int i = 0; i < bytes.length; i++) {
            bytes[i]= (byte) (255-bytes[i]);
        }
        return bytes;
    }
}
