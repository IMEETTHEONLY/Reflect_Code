package mydynamicprory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {

    /*
    *        这个方法是专门用来获取代理对象的工具类
    *
    *       形参:被代理的对象
    *       返回值:返回一个代理对象
    *
    * */

    //因为代理对象和被代理的对象都会实现中介方法，所以说代理对象是star的子类，可以返回star这是多态的表现
    public static Star createProxy(BigStar bigStar){

        //它有三个参数，理解参数就是代理的难点
       Star s =(Star)Proxy.newProxyInstance(
                ProxyUtil.class.getClassLoader(),  //第一个参数是用本类(ProxyUtil)去加载这个代理对象到内存中
                new Class[]{Star.class},   //这里要哪些方法要被代理，主要是中介接口里面的方法,可以是数组
                //这个invoke方法就是要具体实现的代理方法
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //参数proxy表示本对象，一般不需要管

                        //method表示被调用的代理方法
                        if(method.getName().equals("sing")){
                            System.out.println("准备话筒，收钱");
                        }else {
                            System.out.println("准备场地,收钱");
                        }
                        //利用反射去调用，被调用方法的方法
                        return   method.invoke(bigStar,args);  //这个args就是要传递给被调用方法的参数,这个方法调用要被返回回去
                    }
                }


        );
               return s;

    }

}
