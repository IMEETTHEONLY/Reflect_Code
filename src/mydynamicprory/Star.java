package mydynamicprory;
//这个是代理对象和要被代理的对象的中介,即要被代理的方法都会被在这里声明
public interface Star {
    //声明方法

    //唱歌方法
    public abstract String sing(String name);

    //跳舞方法
    public abstract void dance();
}
