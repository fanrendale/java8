package java8.stream;

import org.junit.Test;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * map的高级操作
 *
 * @Author: xjf
 * @Date: 2019/10/17 23:59
 */
public class Test2 {

    @Test
    public void test1(){
        List<Person> personList =
                Person.personList
                .stream()
                .filter(person -> person.name.startsWith("P"))
                .collect(Collectors.toList());

        System.out.println(personList);

        System.out.println("===============================分割线=================================");

        Set<Person> personSet = Person.personList
                .stream()
                .filter(person -> person.name.startsWith("P"))
                .collect(Collectors.toSet());
        System.out.println(personSet);

        System.out.println("===============================分割线=================================");

        //根据年龄分组
        Map<Integer, List<Person>> collect =
                Person.personList
                .stream()
                .collect(Collectors.groupingBy(person -> person.getAge()));

        collect.forEach((key,value) -> System.out.println("key: " + key + "     value: " + value));

        System.out.println("===============================分割线=================================");

        //计算年龄平均值
        Double collect1 = Person.personList
                .stream()
                .collect(Collectors.averagingDouble(person -> person.getAge()));

        System.out.println(collect1);

        System.out.println("===============================分割线=================================");

        //统计，最小年龄、最大年龄、算术平均年龄、总和和数量
        IntSummaryStatistics collect2 = Person.personList
                .stream()
                .collect(Collectors.summarizingInt(p -> p.getAge()));
        System.out.println(collect2.getAverage());

        System.out.println("===============================分割线=================================");

        //进行连接，连成一个字符串
        String collect3 = Person.personList
                .stream()
                .filter(person -> person.getAge() >= 18)
                .map(person -> person.getName())
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age. "));
        System.out.println(collect3);

        System.out.println("===============================分割线=================================");

    }
}
