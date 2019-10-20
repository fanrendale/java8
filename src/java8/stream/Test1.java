package java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: xjf
 * @Date: 2019/10/8 22:52
 */
public class Test1 {

    /**
     * 初始stream
     */
    @Test
    public void test1(){
        List<String> list = Arrays.asList("a1","a2","b1","c2","c1");

        list
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * 创建数据流
     */
    @Test
    public void test2(){
        Arrays.asList("a1","a2","a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);

        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);
    }

    /**
     * 用于处理基本数据类型int、long和double的基本数据流
     */
    @Test
    public void test3(){
        IntStream.range(1,4)
                .forEach(System.out::println);

        Arrays.stream(new int[]{1,2,3})
                .map(n->n*2+1)
                .average()
                .ifPresent(System.out::println);
    }

    /**
     * 对象数据流和基本数据流的互相转换
     */
    @Test
    public void test4(){
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);

        System.out.println("======================================分割符=============================");

        IntStream.range(1,4)
                .mapToObj(n->"a" + n)
                .forEach(System.out::println);

        System.out.println("======================================分割符=============================");

        Stream.of(1.0, 2.0, 3.0, 4.0, 5.0)
                .mapToInt(Double::intValue)
                .mapToObj(n->"a" + n)
                .forEach(System.out::println);
    }

    /**
     * 处理顺序
     */
    @Test
    public void test5(){
        //执行这段代码时，不向控制台打印任何东西。这是因为衔接操作只在终止操作调用时被执行
        //原始的方法会在数据流的所有元素上，一个接一个地水平执行所有操作。但是每个元素在调用链上垂直移动
        Stream.of("d2","a1","c2", "f4","d1")
                .filter(s -> {
                    System.out.println("filter:" + s);
                    return true;
                })
                .forEach(s->System.out.println("foreach:" + s));

        /**
         * allMatch——检查是否匹配所有元素
         *
         * anyMatch——检查是否至少匹配一个元素
         *
         * noneMatch——检查是否没有匹配的元素
         *
         * findFirst——返回第一个元素
         *
         * findAny——返回当前流中的任意元素
         *
         * count——返回流中元素的总个数
         *
         * max——返回流中最大值
         *
         * min——返回流中最小值
         *
         *
         * 说明：只要提供的数据元素满足了谓词，anyMatch操作就会返回true。对于第二个传递"A2"的元素，它的结果为真。
         * 由于数据流的链式调用是垂直执行的，map这里只需要执行两次。所以map会执行尽可能少的次数，而不是把所有元素都映射一遍
         */
        Stream.of("d2","a1","c2", "f4","d1")
                .map(s -> {
                    System.out.println("map: " + s);

                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);

                    return s.startsWith("A");
                });
    }


}
