package java8.lambda;

/**
 * @Author: xjf
 * @Date: 2019/10/19 22:02
 */
@FunctionalInterface
public interface MyFun2<T,R> {

    R execute(T t1,T t2);
}
