package java8.interface_test;

/**
 * @Author: xjf
 * @Date: 2019/10/28 22:55
 */
public interface MyInterface {

    default String getName(){
        return "MyInterface";
    }

    static String show(){
        return "MyInterface中的静态方法";
    }
}
