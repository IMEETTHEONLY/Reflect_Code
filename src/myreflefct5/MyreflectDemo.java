package myreflefct5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class MyreflectDemo {
        //通过配置信息新建对象，并且调用它的方法
        /*
        *   先通过反射获取构造方法new一个对象
        *   再通过反射获取方法，invoke将对象和参数传递过去()这样就可以调用方法了
        * */
    public static void main(String[] args) throws IOException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //先从配置文件当中读取类信息和方法
        Properties properties=new Properties();
        //读取配置文件的内容
        InputStream inputStream=new FileInputStream("D:\\java_code\\Reflect_Code\\prop.properties");
        properties.load(inputStream);

        //获取类名
        String classname = (String) properties.get("classname");

        //获取方法名
        String method= (String) properties.get("method");

//        System.out.println(classname);
//        System.out.println(method);

        //先获取到student类的字节码文件才能获取到构造方法
        Class aClass = Class.forName(classname);

        //调用构造方法
        //以防它是私有的，一律使用最高级
        Constructor<Student> declaredConstructor = aClass.getDeclaredConstructor();
        //取消临时访问检验
        declaredConstructor.setAccessible(true);
        Object O= declaredConstructor.newInstance();

        //获取成员方法并允许
        Method method1 = aClass.getDeclaredMethod(method);
        //取消检验权限
        method1.setAccessible(true);
        //调用方法要将自己的对象传递过去，所以说在前面才需要进行对象的初始化
        method1.invoke(O);


    }
}
