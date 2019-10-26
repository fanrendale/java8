package java8.stream;

import java8.lambda.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 归约与收集
 *
 * @Author: xjf
 * @Date: 2019/10/26 14:30
 */
public class StreamTest5 {

    /**
     * 归约
     * reduce——可以将流中元素反复结合起来，得到一个值
     */
    @Test
    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("===========================分隔符================================");

        Optional<Double> op1 = Employee.employeeList
                .stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        op1.ifPresent(System.out::println);

        System.out.println("===========================分隔符================================");

        Optional<String> op2 = Employee.employeeList
                .stream()
                .map(Employee::getName)
                .reduce((x, y) -> x + y);
        op2.ifPresent(System.out::println);

    }

    /**
     * 收集
     * collect——将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test2(){
        List<String> collect = Employee.employeeList
                .stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("===========================分隔符================================");

        Set<String> collect1 = Employee.employeeList
                .stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        collect1.forEach(System.out::println);

        System.out.println("===========================分隔符================================");

        //可以以任何集合进行收集
        HashSet<String> collect2 = Employee.employeeList
                .stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        collect2.forEach(System.out::println);
    }

    /**
     * 统计操作
     */
    @Test
    public void test3(){
        //总数
        Long collect = Employee.employeeList
                .stream()
                .collect(Collectors.counting());
        System.out.println(collect);

        System.out.println("===========================分隔符================================");

        //求和
        Double collect1 = Employee.employeeList
                .stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect1);

        System.out.println("===========================分隔符================================");

        //平均值
        Double collect2 = Employee.employeeList
                .stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect2);

        System.out.println("===========================分隔符================================");

        //最大值
        Optional<Employee> collect3 = Employee.employeeList
                .stream()
                .collect(Collectors.maxBy((a, b) -> Integer.compare(a.getAge(), b.getAge())));
        collect3.ifPresent(System.out::println);

        System.out.println("===========================分隔符================================");

        //最小值
        Optional<Integer> collect4 = Employee.employeeList
                .stream()
                .map(Employee::getAge)
                .collect(Collectors.minBy((a, b) -> Integer.compare(a, b)));
        collect4.ifPresent(System.out::println);
    }

    /**
     * 分组
     */
    @Test
    public void test4(){
        Map<Employee.Status, List<Employee>> collect = Employee.employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
    }

    /**
     * 多级分组:支持无限极的分组，可以往里面分无数次组
     */
    @Test
    public void test5(){
        Map<Employee.Status, Map<String, Map<String, List<Employee>>>> collect = Employee.employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    Employee temp = (Employee) e;
                    if (temp.getAge() < 35) {
                        return "青年";
                    } else if (temp.getAge() < 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                }, Collectors.groupingBy(Employee::getName))));

        System.out.println(collect);
    }

    /**
     * 分片
     */
    @Test
    public void test6(){
        Map<Boolean, List<Employee>> collect = Employee.employeeList
                .stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));
        System.out.println(collect);
    }

    /**
     * 方便获取：最大值、最小值、平均值、总和值、总数。
     */
    @Test
    public void test7(){
        DoubleSummaryStatistics collect = Employee.employeeList
                .stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getMax());
        System.out.println(collect.getAverage());
        System.out.println(collect.getCount());
        System.out.println(collect.getMin());
        System.out.println(collect.getSum());
    }

    /**
     * 连接
     */
    @Test
    public void test8(){
        String collect = Employee.employeeList
                .stream()
                .map(Employee::getName)
                .collect(Collectors.joining("-","start","end"));
        System.out.println(collect);
    }
}
