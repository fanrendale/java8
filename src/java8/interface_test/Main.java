package java8.interface_test;

/**
 * 接口中的默认方法使用：
 *
 * ①接口默认方法的“类优先”原则：
 *     若一个接口中定义了一个默认方法，而另外一个父类又定义了一个同名的方法时，
 *     子类执行这个方法会选择父类的方法，接口中相同名称和参数的默认方法会被忽略
 *
 * ②接口冲突：如果一个父接口提供一个默认方法，而另一个接口也提供了一个具有相同名称
 *          和参数列表的方法（不管方法是否是默认方法），那么必须覆盖该方法来解决冲突
 *
 *
 *
 * @Author: xjf
 * @Date: 2019/10/28 22:59
 */
public class Main {

    public static void main(String[] args) {
        //“类优先”：
        //此处SubClass继承的类MyClass和实现的接口MyInterface, 都有getName()方法的实现
        //在调用时，使用的是类的getName()方法
        SubClass subClass = new SubClass();
        System.out.println(subClass.getName());

        //解决接口冲突：两个接口都有相同方法时，子类必须要自己实现
        SubClass2 subClass2 = new SubClass2();
        System.out.println(subClass2.getName());

        //接口中可以有静态方法
        System.out.println(MyInterface.show());
    }
}
