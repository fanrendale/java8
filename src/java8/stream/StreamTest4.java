package java8.stream;

import java8.lambda.Employee;
import java8.lambda.Employee.Status;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 终止操作：
 *
 * 查找与匹配：
 *      allMatch——检查是否匹配所有元素
 *      anyMatch——检查是否至少匹配一个元素
 *      noneMatch——检查是否没有匹配所有元素
 *      findFirst——返回第一个元素
 *      findAny——返回当前流中的任意元素（使用并行流效果更好，随机）
 *      count——返回流中元素的总个数
 *      max——返回流中最大值
 *      min——返回流中最小值
 *
 * @Author: xjf
 * @Date: 2019/10/24 22:51
 */
public class StreamTest4 {

    /**
     * 数据
     */
    static List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 18, 9999.99, Status.BUSY),
            new Employee("李四", 38, 5555.99, Status.FREE),
            new Employee("王五", 50, 6666.66, Status.VACATION),
            new Employee("赵六", 16, 3333.33, Status.BUSY),
            new Employee("田七", 8, 7777.77, Status.FREE)
    );

    /**
     * 匹配操作
     */
    @Test
    public void test1(){
        boolean b1 = employeeList.stream()
                .allMatch(employee -> Status.BUSY.equals(employee.getStatus()));
        System.out.println(b1);

        boolean b2 = employeeList.stream()
                .anyMatch(employee -> Status.BUSY.equals(employee.getStatus()));
        System.out.println(b2);

        boolean b3 = employeeList.stream()
                .noneMatch(employee -> Status.BUSY.equals(employee.getStatus()));
        System.out.println(b3);

        Optional<Employee> first = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getAge))
                .findFirst();

        first.ifPresent(System.out::println);

        Optional<Employee> any = employeeList.parallelStream()
                .filter(employee -> Status.FREE.equals(employee.getStatus()))
                .findAny();
        any.ifPresent(System.out::println);
    }

    /**
     * 查找
     */
    @Test
    public void test2(){
        long count = employeeList.stream()
                .count();
        System.out.println(count);

        Optional<Employee> max = employeeList.stream()
                .max(Comparator.comparing(Employee::getSalary));
        max.ifPresent(System.out::println);

        Optional<Integer> min = employeeList.stream()
                .map(Employee::getAge)
                .min(Double::compare);
        min.ifPresent(System.out::println);
    }
}
