package myreflect1;

public class MyReflectDemo1 {
    /*
    *   获取反射的三种方式:
    *   1.Class.forName("全类名")  报名+类名
    *   2.类型.class
    *   3.对象.getClass()    这个方式是定义在object里面的所以说每个类都拥有
    *
    *
    * */
    public static void main(String[] args) throws ClassNotFoundException {
        //第一种获取方法
        //这个方法是比较通用的方法，比较实用
        Class clazz1 = Class.forName("myreflect1.Student");
        System.out.println(clazz1);

        //
        Class<Student> studentClass = Student.class;

    }
}
