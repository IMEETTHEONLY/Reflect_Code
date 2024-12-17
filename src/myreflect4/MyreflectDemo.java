package myreflect4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class MyreflectDemo {
    //利用反射的知识，将对象的值存储到文件当中去

    public static void main(String[] args) throws IOException, IllegalAccessException {
        Student s=new Student("zhangsan",23);

        Teacher t=new Teacher("wangwu",55);

        saveObject(t);

    }

    //利用反射写道文件当中去
    public static void saveObject(Object o) throws IllegalAccessException, IOException {

        //获取文件的字节码文件
        Class clazz = o.getClass();

        BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\java_code\\Reflect_Code\\a.txt"));

        //获取所有的成员变量
        Field[] fields = clazz.getDeclaredFields();
        //遍历成员变量
        for (Field field : fields) {
            //取消检查
            field.setAccessible(true);
            //获取成员变量的名字
            String name = field.getName();
            //获取成员变量的值
            Object value = field.get(o);
            //写道文件
            bw.write(name+"="+value);
            bw.newLine();

        }
        //关流
        bw.close();

    }
}
