package java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * java8内置的四大内置核心函数式接口
 *
 * @Author: xjf
 * @Date: 2019/10/20 0:01
 */
public class LambdaTest4 {

    /**
     * 1、Consumer<T>: 消费型函数式接口
     */
    @Test
    public void test1(){
        buyFruits(100,con -> System.out.println("买水果花费：" + con + "元"));
    }

    /**
     * 买水果
     * @param money
     * @param consumer
     */
    private void buyFruits(Integer money, Consumer<Integer> consumer){
        consumer.accept(money);
    }

    /**
     * 2、Supplier<T>: 供给型函数式接口
     */
    @Test
    public void test2(){
        List<Integer> integerList = createNums(10, () -> (int) (Math.random() * 1000));
        integerList.forEach(System.out::println);

    }

    /**
     * 生成count个随机数
     * @param count
     * @param supplier
     * @return
     */
    private List<Integer> createNums(Integer count, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }

        return list;
    }

    /**
     * 3、Function<T,R>: 函数型接口
     */
    @Test
    public void test3(){
        String resultStr = executeStr("\t\t\t xjf真帅啊!    ", (str) -> str.trim());
        System.out.println(resultStr);

        String resultStr2 = executeStr("xjf真帅啊!", str -> str.substring(3, 5));
        System.out.println(resultStr2);
    }

    /**
     * 处理字符串
     * @param str
     * @param function
     * @return
     */
    private String executeStr(String str, Function<String, String> function){
        return function.apply(str);
    }

    /**
     * 4、Predicate<T>: 断言型接口
     */
    @Test
    public void test4(){
        List<String> list = Arrays.asList("Redis","Spring","MongoDB","Mysql","Java","Python");

        List<String> resultList = filterStr(list, s -> s.contains("M"));
        resultList.forEach(System.out::println);

    }

    /**
     * 对满足条件的字符串进行过滤
     * @param list
     * @param predicate
     * @return
     */
    private List<String> filterStr(List<String> list, Predicate<String> predicate){
        //方法一
        /*List<String> tempList = new ArrayList<>();

        list.forEach(s -> {
            if (predicate.test(s)){
                tempList.add(s);
            }
        });

        return tempList;*/

        //方法二
        return list.stream().filter(predicate).collect(Collectors.toList());
    }
}
