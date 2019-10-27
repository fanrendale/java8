package java8.stream;

import org.junit.Test;

import java.time.Clock;
import java.util.stream.LongStream;

/**
 * 串行流与并行流
 *
 * ①并行流就是把一个内容分成多个数据块，并用不同额线程分别处理每个数据块的流
 *
 * ②Java8中将并行进行了优化，我们可以很容易的对数据进行并行操作。Stream API 可以声明性地通过parallel()
 * 与sequential()在并行流与顺序流之间进行切换
 *
 * ③java8的并行流使用到了Fork/Join框架：就是在必要的情况下，将一个大任务，进行拆分成若干个小任务（拆到
 * 不可再拆时），再将一个个的小任务运算的结果进行join汇总。
 *  Fork/Join框架采用“工作窃取”模式（work-stealing):当执行新的任务时它可以将其拆分分成更小的任务执行，并将
 *  小任务加到线程队列中，然后再从一个随机线程的队列中偷一个并把它放在自己的队列中。
 *  这种方式减少的线程的等待时间，提高了性能。
 *
 * @Author: xjf
 * @Date: 2019/10/27 12:54
 */
public class StreamTest6 {

    @Test
    public void test1(){
        Clock clock = Clock.systemDefaultZone();

        long start = clock.millis();

        long reduce = LongStream.rangeClosed(0, 100000000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(reduce);

        long end = clock.millis();

        System.out.println("用时：" + (end - start) + "ms");

        //串行用时：36574 ms
        //并行用时：8871 ms
    }
}
