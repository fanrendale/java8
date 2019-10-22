package java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Steam的三个操作步骤：
 *      1.创建流
 *      2.流的中间操作
 *      3.流的终止操作（终端操作）
 *
 * @Author: xjf
 * @Date: 2019/10/21 23:15
 */
public class StreamTest1 {

    /**
     * 创建流的四种方式
     */
    @Test
    public void test1(){
        //1.可以通过Collection系列集合提供的stream()或者parallelStream()创建流
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过数组创建流
        Person[] people = new Person[10];
        Stream<Person> stream2 = Arrays.stream(people);

        //3.使用Stream类的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        //4.创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, x -> x + 2);
        stream4.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
