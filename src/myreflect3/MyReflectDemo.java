package myreflect3;

import java.lang.reflect.Field;

public class MyReflectDemo {
        //它的获取方式和获取构造函数的方式一模一样的
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        //利用反射获取到成员变量的一系列属性和值
        Class clazz = Class.forName("myreflect3.Student");

        //获取私有的成员变量
//        Field[] fields = name.getFields();
//        for (Field field : fields) {
//            System.out.println(field);
//        }
//

        //获取单个成员变量(这里获取私有的为例)
        Field age = clazz.getDeclaredField("age");
        System.out.println(age);

        //可以获取权限访问符
        int i = age.getModifiers();
        System.out.println(i);

        //获取变量的名字
        String name = age.getName();
        System.out.println(name);

        //获取私有变量的类型
        Class<?> type = age.getType();
        System.out.println(type);

        //获得成员变量记录的值
        //暂时取消检验
        age.setAccessible(true);
        Student s=new Student("张三",15);
        int o =(int) age.get(s);
        System.out.println(o);

        //修改对象里面记录的值
        age.setAccessible(true);
        age.set(s,25);
        System.out.println(s);



    }
}
