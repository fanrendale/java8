package java8.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 做题测试
 *
 * @Author: xjf
 * @Date: 2019/10/19 16:17
 */
public class LambdaTest3 {

    /**
     * 数据
     */
    List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 16, 9999.99),
            new Employee("李四", 38, 5555.99),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    @Test
    public void test1(){
        Collections.sort(employeeList,(a,b) ->{
            if (!a.getAge().equals(b.getAge())){
                return a.getAge().compareTo(b.getAge());
            }else {
                return a.getName().compareTo(b.getName());
            }
        });

        employeeList.forEach(System.out::println);
    }

    @Test
    public void  test2(){
        String str = "abcde";
        String s1 = toUpper(str, s -> {
            s = s.toUpperCase();
            return s.substring(2, 5);
        });

        System.out.println(s1);

        System.out.println("==============================分隔符===============================");

        String s2 = toUpper2(str, String::toUpperCase);
        System.out.println(s2);

        String s3 = toUpper2(s2,s -> s.substring(2,5));
        System.out.println(s3);
    }

    public String toUpper(String str, MyFun myFun){

        return myFun.getValue(str);
    }

    public String toUpper2(String str, Function<String,String> function){
        return function.apply(str);
    }


    @Test
    public void test3(){
        Long aLong = executeLong(10L, 12L, (num1, num2) -> num1 + num2);
        System.out.println(aLong);

        Long aLong1 = executeLong(10L, 12L, (num1, num2) -> num1 * num2);
        System.out.println(aLong1);

        System.out.println("==============================分隔符===============================");

        Long value1 = executeLong2(10L,12L,LambdaTest3::add);
        System.out.println(value1);

        Long value2 = executeLong2(10L,12L,LambdaTest3::multi);
        System.out.println(value2);
    }

    public Long executeLong(Long num1, Long num2, MyFun2<Long,Long> myFun2){
        return myFun2.execute(num1,num2);
    }

    public Long executeLong2(Long num1, Long num2, BiFunction<Long, Long, Long> bf){
        return bf.apply(num1,num2);
    }

    public static Long add(Long num1, Long num2){
        return num1 + num2;
    }

    public static Long multi(Long num1, Long num2){
        return num1 * num2;
    }
}
