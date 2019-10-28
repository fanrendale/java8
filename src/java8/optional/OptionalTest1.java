package java8.optional;

import java8.lambda.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional容器类常用方法：
 * 1. Optional.of(T t) : 创建一个Optional实例
 * 2. Optional.empty() : 创建一个空的Optional实例
 * 3. Optional.ofNullable(T t) : 若t 不为null，创建Optional实例，否则创建空实例
 * 4. isPresent() : 判断是否包含控制
 * 5. orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
 * 6. orElseGet(Supplier s) : 如果调用对象包含值，返回该值，否则返回s获取的值。参数是个供给型函数式接口，可以做很多自己的操作
 * 7. map(Function f) : 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
 * 8. flatMap(Function mapper) : 与map类似，要求返回值必须是Optional
 *
 * @Author: xjf
 * @Date: 2019/10/27 22:33
 */
public class OptionalTest1 {

    @Test
    public void test1() {
        Optional<Employee> optionalEmployee = Optional.of(null);
        Employee employee = optionalEmployee.get();
        System.out.println(employee);
    }

    @Test
    public void test2() {
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.get());
    }

    /**
     * orElse()与orElseGet()区别：orElse()参数是一个方法，而orElseGet()的参数是供给型函数式接口，可以做很多的操作，
     * 还有区别查看：{@link Test1#test4} java8.optional.Test1里面
     */
    @Test
    public void test3() {
        Optional<Employee> optionalEmployee = Optional.ofNullable(null);

        /*if (optionalEmployee.isPresent()){
            System.out.println(optionalEmployee.get());

        }*/

        Employee employee = optionalEmployee.orElse(new Employee("张三"));
        System.out.println(employee);

        Employee employee1 = optionalEmployee.orElseGet(() -> new Employee("李四"));
        System.out.println(employee1);
    }

    /**
     * map和flatmap区别：map返回的是一个对象，而flatmap需要返回的还是Optional
     * <p>
     * flatmap是进一步防止空指针
     */
    @Test
    public void test4() {
        Optional<Employee> op = Optional.ofNullable(new Employee("王五", 25, 2333.33));
        Optional<String> optional = op.map(Employee::getName);
        System.out.println(optional.get());

        Optional<String> optional1 = op.flatMap(employee -> Optional.of(employee.getName()));
        System.out.println(optional1.get());
    }

    /**
     * 例题：获取男人心中的女神
     */
    @Test
    public void test5() {
        Goddness goddness = new Goddness("范冰冰");
        Man man = new Man();

        String name = getMansGoddness(man);
        System.out.println("女神名字：" + name);

        System.out.println("===========================分隔符================================");

        String name2 = getMansGoddness2(man);
        System.out.println(name2);
    }

    /**
     * 获取的方法
     */
    public String getMansGoddness(Man man) {
        if (man != null) {
            Goddness goddness = man.getGoddness();
            if (goddness != null) {
                return goddness.getName();
            }
        }

        //默认值
        return "刘亦菲";
    }

    public String getMansGoddness2(Man man) {
//        return Optional.ofNullable(Optional.ofNullable(man)
//                                           .orElse(new Man())
//                                           .getGoddness())
//                       .orElse(new Goddness("刘亦菲"))
//                       .getName();
        man = null;

        //? 此处的man为null时会自动创建新的Man吗？
        // 以下为解决问题的结构，只要不满足条件就会调用orElse()方法
       return Optional.ofNullable(man)
               .map(m -> {
                   System.out.println("第一个map");
                   return m.getGoddness();
               })
               .map(g -> {
                   System.out.println("第二个map");
                   return g.getName();
               })
               //默认值
               .orElse("刘亦菲");
    }
}
