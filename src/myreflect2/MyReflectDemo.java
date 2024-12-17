package myreflect2;

import myreflect3.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MyReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        /*
        *       只能获取public的方法且加了s获得所有匹配上的构造方法:getConstructors
        *       获取的方法且加了s获得所有匹配上的构造方法:getDeclaredConstructors
        *
        *
        * */

        //所有构造方法都在字节码文件当中的
        Class clazz = Class.forName("myreflect3.Student");

        //获取所有公共构造方法
//        Constructor[] constructors1 = clazz.getConstructors();
//        for (Constructor constructor : constructors1) {
//            System.out.println(constructor);
//        }

//        //获取所有构造方法
//        Constructor[] constructors2 = clazz.getDeclaredConstructors();
//
//        for (Constructor constructor : constructors2) {
//            System.out.println(constructor);
//        }

        //获取某个私有的构造方法
        Constructor constructor3 = clazz.getDeclaredConstructor(String.class, int.class);
        System.out.println(constructor3);

        //我可以获取获取到构造方法的所有信息
        int i = constructor3.getModifiers();  //权限修饰符
        System.out.println(i);  //  2表示private,它有一个反射对应变量表

        //可以用构造方法进行构造
        //这里要临时提升权限才能访问private
        constructor3.setAccessible(true);
        Student s = (Student) constructor3.newInstance("张三",23);
        System.out.println(s);


    }
}
