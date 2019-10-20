package java8.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @Author: xjf
 * @Date: 2019/10/6 11:40
 */
public class DateTest {

    /**
     * 带时区的日期和时间
     */
    @Test
    public void test1(){
        LocalDateTime dateTime = LocalDateTime.of(2016, Month.APRIL,14,14,2,24);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(dateTime,offset);
        System.out.println("日期和时间在时区上的偏移时间：" + date);
    }

    /**
     * 获取当前时间戳
     */
    @Test
    public void test2(){
        Instant timestamp = Instant.now();
        System.out.println("当前的时间戳是：" + timestamp);

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
    }

    /**
     * 日期进行解析/格式化
     */
    @Test
    public void test3(){
        String dayAfterTommorrow = "20191006";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(formatted);
    }

    /**
     * 自定义格式器来解析日期
     */
    @Test
    public void test4(){
        String dateString = "06 10 2019";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate localDate = LocalDate.parse(dateString,formatter);
        System.out.println(localDate);
    }

    /**
     * 日期转字符串
     */
    @Test
    public void test5(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateString = localDateTime.format(formatter);

        System.out.println(dateString);


    }


}
