package java8.stream;

import java8.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 映射
 * map——接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。    返回的是对象
 * flatMap——接收一个函数作为函数，将流中的每个值都换成另一流，然后把所有流连接成一个流       返回的是流
 *
 * @Author: xjf
 * @Date: 2019/10/23 22:41
 */
public class StreamTest3 {

    @Test
    public void test1(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

        Stream<String> stream1 = list.stream()
                .map(String::toUpperCase);
        stream1.forEach(System.out::println);

        System.out.println("===========================分隔符================================");

        Stream<String> stream2 = StreamTest2.employeeList
                .stream()
                .map(Employee::getName);
        stream2.forEach(System.out::println);

        System.out.println("===========================分隔符================================");
        //结果：{{a,a,a},{b,b,b}...}
        Stream<Stream<Character>> streamStream = list.stream()
                .map(StreamTest3::filterCharacter);

        streamStream.forEach(value -> value.forEach(System.out::println));

        System.out.println("===========================分隔符================================");
        //结果：{a,a,a,b,b,b...}
        Stream<Character> characterStream = list.stream()
                .flatMap(StreamTest3::filterCharacter);

        characterStream.forEach(System.out::println);

    }

    /**
     * 将字符串中的每个字符以流的形式返回
     *
     * @param str
     * @return
     */
    static private Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for (char c : str.toCharArray()) {
            list.add(c);
        }

        return list.stream();
    }

    /**
     * map与flatMap的效果，跟集合的add和addAll类似
     */
    @Test
    public void test2(){
        List list = new ArrayList();
        List<String> list1 = Arrays.asList("aa","bb","cc");

        list.add(11);
        list.add(22);
        list.add(list1);
        list.addAll(list1);

        System.out.println(list);
    }
}
