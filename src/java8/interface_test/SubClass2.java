package java8.interface_test;

/**
 * @Author: xjf
 * @Date: 2019/10/28 23:10
 */
public class SubClass2 implements MyInterface, MyInterface2 {
    @Override
    public String getName() {
        return "SubClass2";
    }
}
