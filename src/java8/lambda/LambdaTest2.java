package java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一、测试Lambda不同的语法格式
 *
 * 二、Lambda需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。
 *            可以使用注解@FunctionalInterface修饰，检查是否是函数式接口
 *
 * @Author: xjf
 * @Date: 2019/10/19 15:45
 */
public class LambdaTest2 {

    /**
     * 语法格式一：无参数，无返回值
     */
    @Test
    public void test1(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        };

        System.out.println("用匿名内部类实现runnable: ");
        runnable.run();

        System.out.println("===========================分隔符================================");

        Runnable runnable1 = () -> System.out.println("runnable1");
        System.out.println("使用lambda实现runnable：");
        runnable1.run();
    }

    /**
     * 语法格式二：一个参数，无返回值
     */
    @Test
    public void test2(){
//        Consumer<String> consumer = (x) -> System.out.println(x);
        Consumer<String> consumer = System.out::println;

        consumer.accept("xjf");
    }

    /**
     * 语法格式三：两个参数，有返回值
     */
    @Test
    public void test3(){
        Comparator<Integer> comparator = (a,b) -> {
            System.out.println("a=" + a + ";  b=" + b);
            return Integer.compare(a,b);
        };

        //如下情况可简写
        Comparator<Integer> comparator1 = (a,b) -> Integer.compare(a,b);
        Comparator<Integer> comparator2 = Integer::compare;
    }
}
