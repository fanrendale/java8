package java8.optional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @Author: xjf
 * @Date: 2019/10/19 13:57
 */
public class Test2 {

    /**
     * 使用map转换optional的值
     */
    @Test
    public void test1(){
        User user = new User("xjf", 24);
        String name = Optional.ofNullable(user).map(User::getName).orElse("dale");

        assertEquals(name,user.getName());
        System.out.println(name);
    }

    /**
     * 对于Optional的map和flatmap：
     * map是把结果自动封装成一个Optional，但是flatmap需要你自己去封装
     */
    @Test
    public void test2(){
        User user = new User("xjf", 24);
        user.setPosition("Developer");
        String position = Optional.ofNullable(user).flatMap(User::getPosition).orElse("default");

        assertEquals(position,user.getPosition().get());
        System.out.println(position);
    }

    /**
     * 过滤值
     */
    @Test
    public void test3(){
        User user = new User("xjf",23);
        Optional<User> optionalUser = Optional.ofNullable(user).filter(u -> u.getName() != null && u.getName().contains("j"));
        assertTrue(optionalUser.isPresent());
        System.out.println(optionalUser.get());
    }

    /**
     * 使用Stream返回Optional对象的findFirst方法
     */
    @Test
    public void test4(){
        List<User> list = new ArrayList<>();
        User user = list.stream().findFirst().orElse(new User("default", 33));
        System.out.println(user);

        Optional.ofNullable(null);
    }
}
