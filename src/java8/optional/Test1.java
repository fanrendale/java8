package java8.optional;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @Author: xjf
 * @Date: 2019/10/19 10:38
 */
public class Test1 {

    @Test
    public void test1(){
        String name = "xjf";
        Optional<String> optional = Optional.ofNullable(name);

        assertEquals("xjf",optional.get());
    }

    @Test
    public void test2(){
//        User user = new User("xjf",24);
        User user = null;
        Optional<User> optional = Optional.ofNullable(user);

        //判断是否有值
//        assertTrue(optional.isPresent());
//
//        assertEquals(user.getName(), optional.get().getName());

        //使用lambda
        optional.ifPresent(u -> assertEquals(user.getName(),u.getName()));
    }

    @Test
    public void test3(){
        User user = null;
//        User user = new User("dale", 18);
        User user1 = new User("xjf", 24);

        //如果user不为空，则返回user，否则返回user1
//        User result = Optional.ofNullable(user).orElse(user1);
        User result = Optional.ofNullable(user).orElseGet(() -> user1);

        assertEquals(user1.getName(), result.getName());
    }

    /**
     * orElse()和orElseGet()方法的区别
     *
     * 1.当判断的对象都为null时，两个都会调用参数，创建新的对象
     * 2.当判断的对象都不为null时，orElse()方法任然会执行参数，而orElseGet不会执行
     */
    @Test
    public void test4(){
//        User user = null;
        User user = new User("dale",33);

        System.out.println("use orElse():");
        User result = Optional.ofNullable(user).orElse(createNewUser());

        System.out.println("use orElseGet():");
        User result2 = Optional.ofNullable(user).orElseGet(this::createNewUser);
    }

    /**
     * orElseThrow()方法：如果对象为空，抛出指定的异常
     */
    @Test
    public void test5(){
        User user = null;
        User result = Optional.ofNullable(user).orElseThrow(() -> new IndexOutOfBoundsException("数组越界"));
    }

    /**
     * 内部方法：创建新用户
     * @return
     */
    private User createNewUser(){
        System.out.println("invoke createNewUser()");
        return new User("xjf", 24);
    }
}
