package mydynamicprory;
//这个是要被代理的对象

//被代理的对象要实现中介方法
public class BigStar implements Star{
    private String name;

    //唱歌方法
    @Override
    public String sing(String name){
        System.out.println(this.name+"正在唱"+name);
        return "谢谢";
    }

    //跳舞方法
    @Override
    public void dance(){
        System.out.println("跳舞");
    }

    public BigStar() {
    }

    public BigStar(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "BigStar{name = " + name + "}";
    }
}
