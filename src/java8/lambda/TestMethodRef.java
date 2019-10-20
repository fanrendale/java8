package java8.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 一、方法引用：若Lambda表达式的方法体已经有方法实现了，则可以使用“方法引用”
 *          （可以理解为方法引用是Lambda表达式的另外一种表现形式）
 *
 * 主要有三种语法格式：
 *
 * 1、对象::实例方法名
 *
 * 2、类::静态方法名
 *
 * 3、类::实例方法名
 *
 * 注意：
 *      ①Lambda方法体中调用的方法的参数和返回类型 ，要与函数式接口的抽象方法的参数和返回值类型一致
 *      ②当lambda的方法体中，第一个参数是方法的调用者，第二个参数是方法的参数，
 *       则可以使用类名调用实例方法。即下面例子中的x是方法的调用者，y是方法的参数
 *
 * 二、构造器引用
 * 格式：
 *      类名::new
 * 注意：调用的构造器方法的参数列表必须要与函数式接口的参数列表一致
 *
 * 三、数组引用
 * 格式：
 *      type[]::new
 *
 * @Author: xjf
 * @Date: 2019/10/20 22:10
 */
public class TestMethodRef {

    /**
     * 1、对象::实例方法名
     */
    @Test
    public void test1(){
        //形式一
        Consumer<String> consumer = x -> System.out.println(x);

        //形式二
        PrintStream ps = System.out;
        Consumer<String> consumer1 = ps::println;

        //形式三
        Consumer<String> consumer2 = System.out::println;

        consumer2.accept("xjf");
    }

    /**
     * 继续测试第一种格式
     */
    @Test
    public void test2(){
        Employee employee = new Employee("xjf", 24, 5000.00);
        Supplier<String> supplier = () -> employee.getName();
        System.out.println(supplier.get());

        Supplier<Integer> s2 = employee::getAge;
        System.out.println(s2.get());
    }

    /**
     * 2、类::静态方法名
     */
    @Test
    public void test3(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);

        Comparator<Integer> comparator1 = Integer::compareTo;
    }

    /**
     * 3、类::实例方法名
     *
     * 使用该方式的规则：当lambda的方法体中，第一个参数是方法的调用者，第二个参数是方法的参数，
     *                  则可以使用类名调用实例方法。即下面例子中的x是方法的调用者，y是方法的参数
     */
    @Test
    public void test4(){
        BiPredicate<String, String> bp = (x,y) -> x.equals(y);

        //与下等同(此处使用类名调用的实例方法）
        BiPredicate<String, String> bp2 = String::equals;
    }

    /**
     * 构造器引用
     */
    @Test
    public void test5(){
        Supplier<Employee> supplier = () -> new Employee();

        supplier = Employee::new;

        System.out.println(supplier.get());
    }

    @Test
    public void test6(){
        Function<String,Employee> function = (x) -> new Employee(x);

        function = Employee::new;

        System.out.println(function.apply("xjf"));

        System.out.println("==============================分隔符===============================");

        BiFunction<String, Integer, Employee> bf = Employee::new;
        System.out.println(bf.apply("dale", 25));
    }

    /**
     * 数组引用
     */
    @Test
    public void test7(){
        Function<Integer,String[]> fun1 = (x) -> new String[x];
        String[] myArr = fun1.apply(10);
        System.out.println(myArr.length);

        Function<Integer, String[]> fun2 = String[]::new;
        String[] myArr2 = fun2.apply(20);
        System.out.println(myArr2.length);
    }
}
