package java8.lambda;

import org.junit.Test;

import java.util.*;

/**
 * @Author: xjf
 * @Date: 2019/10/6 9:58
 */
public class LambdaTest1 {

    /**
     * 原来匿名内部类
     */
    @Test
    public void test() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    /**
     * Lambda 表达式
     */
    @Test
    public void test1() {
        Comparator<Integer> comparator = (x, y) -> x.compareTo(y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    /**
     * 数据
     */
    List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    /**
     * 需求，获取员工中年龄大于35的信息
     */
    @Test
    public void test2() {
        List<Employee> list = filterEmployees(employeeList);

        for (Employee employee : list) {
            System.out.println(employee);
        }

        System.out.println("-------------------------------------------------");
        List<Employee> list1 = filterEmployees2(employeeList);
        for (Employee employee : list1) {
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();

        for (Employee employee : list) {
            if (employee.getAge() >= 35) {
                emps.add(employee);
            }
        }

        return emps;
    }

    /**
     * 获取工资大于5000的员工
     */
    public List<Employee> filterEmployees2(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();

        for (Employee employee : list) {
            if (employee.getSalary() >= 5000) {
                emps.add(employee);
            }
        }

        return emps;
    }

    /**
     * 优化方式一：策略设计模式
     */
    public List<Employee> filterEmployeesByPredicate(List<Employee> list, MyPredicate<Employee> mp) {
        List<Employee> emps = new ArrayList<>();

        for (Employee employee : list) {
            if (mp.test(employee)) {
                emps.add(employee);
            }
        }

        return emps;
    }

    @Test
    public void test3() {
        List<Employee> list = filterEmployeesByPredicate(employeeList, new FilterEmployeeByAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }

        System.out.println("-------------------------------------------------");

        List<Employee> list1 = filterEmployeesByPredicate(employeeList, new FilterEmployeeBySalary());
        for (Employee employee : list1) {
            System.out.println(employee);
        }
    }

    /**
     * 优化方式二：匿名内部类
     */
    @Test
    public void test4() {
        List<Employee> list = filterEmployeesByPredicate(employeeList, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 5000;
            }
        });

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    /**
     * 优化方式三：lambda表达式
     */
    @Test
    public void test5() {
        List<Employee> list = filterEmployeesByPredicate(employeeList, employee -> employee.getAge() > 35);
        list.forEach(System.out::println);
    }

    /**
     * 优化方式四：Stream API
     */
    @Test
    public void test6() {
        employeeList.stream()
                    .filter(employee -> employee.getSalary() >= 5000)
                    .limit(2)
                    .forEach(System.out::println);

        System.out.println("-------------------------------------------------");

        employeeList.stream()
                    .map(Employee::getName)
                    .forEach(System.out::println);

    }
}
