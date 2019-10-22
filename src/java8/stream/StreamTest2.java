package java8.stream;

import java8.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Steam的三个操作步骤：
 *       1.创建流
 *       2.流的中间操作
 *       3.流的终止操作（终端操作）
 *
 * 二、中间操作：
 *      1.filter: 根据指定条件进行过滤，接收的是一个Predicate的函数式接口
 *      2.limit: 限制个数,使其元素不超过n个
 *      3.skip: 跳过前n个元素，然后再开始执行。若流中元素不足n个，则返回一个空流。与limit(n)互补
 *      4.distinct：进行去重，不过去重依据是根据hashCode和equals方法判断的，因此需要重写这两个方法达到去重
 * @Author: xjf
 * @Date: 2019/10/22 22:52
 */
public class StreamTest2 {

    /**
     * 数据
     */
    List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77),
            new Employee("田七", 8, 7777.77),
            new Employee("田七", 8, 7777.77),
            new Employee("田七", 8, 7777.77)
    );


    /**
     * 内部迭代：迭代操作由Stream API完成
     */
    @Test
    public void test1(){
        //中间操作：没有终止操作时不会执行任何操作
        Stream<Employee> employeeStream
                = employeeList.stream()
                    .filter(e -> {
                        System.out.println("短路");
                        return e.getAge() > 35;
                    }).limit(2);

        //终止操作：一次性执行全部操作，即“惰性求值”
        employeeStream.forEach(System.out::println);
    }

    /**
     * 外部迭代：由我们进行手动循环
     */
    @Test
    public void test2(){
        Iterator<Employee> iterator = employeeList.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test3(){
        employeeList.stream()
                .filter(e -> e.getSalary() > 5000)
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4(){
        employeeList.stream().filter(e -> e.getSalary() > 5000).skip(2).forEach(System.out::println);
    }

    @Test
    public void test5(){
        employeeList.stream()
                .filter(e -> e.getSalary() > 5000)
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }
}
