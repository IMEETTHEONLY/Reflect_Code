package mydynamicprory;

public class Test {

    /*
    *
    *       1.获取代理对象
    *       2.在调用代理对象的唱歌方法
    *
    *
    * */

    public static void main(String[] args) {
        //1.获取代理对象
        Star proxy = ProxyUtil.createProxy(new BigStar("鸡哥"));

        //调用代理方法的方法
        proxy.sing("及你太美");


    }
}
