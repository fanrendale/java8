package java8.time;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 新的时间日期API
 *
 * 新的时间日期API和以前的API区别：
 *             ①以前的时间日期API是线程不安全的，新的是线程安全的
 *             ②新的时间API每一次操作都会返回一个新的实例
 *
 * @Auther: XuJiaFei
 * @Date: 2019/10/29 09:33
 * @Description:
 */
public class TimeTest1 {

    /**
     * 1.获取当前的日期
     */
    @Test
    public void test1(){
        LocalDate now = LocalDate.now();
        System.out.println("今天的日期：" + now);
    }

    /**
     * 2.获取当前的年月日
     */
    @Test
    public void test2(){
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int monthValue = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();

        System.out.println(year + " 年 " + monthValue + " 月 " + dayOfMonth + " 日");
    }

    /**
     * 3.获取某个特定的日期
     */
    @Test
    public void test3(){
        LocalDate localDate = LocalDate.of(2019, 10, 30);
        System.out.println(localDate);
    }

    /**
     * 4.检查两个日期是否相等
     */
    @Test
    public void test4(){
        //LocalDate重写了equals方法来进行日期的比较
        LocalDate localDate = LocalDate.of(2019, 10, 29);
        LocalDate now = LocalDate.now();
        System.out.println("两个日期是否相等：" + localDate.equals(now));
    }

    /**
     * 5.判断月日是否相等，可以用来判断今天是否是你的生日
     */
    @Test
    public void test5(){
        LocalDate birthDay = LocalDate.of(1994, 10, 29);
        LocalDate now = LocalDate.now();

        //生日
        MonthDay birthMonthDay = MonthDay.of(birthDay.getMonthValue(),birthDay.getDayOfMonth());
        //今天
//        MonthDay today = MonthDay.now();
        MonthDay today = MonthDay.from(now);

        if (birthMonthDay.equals(today)){
            System.out.println("今天是你的生日");
        }else {
            System.out.println("今天不是你的生日");
        }
    }

    /**
     * 6.获取当前时间（时分秒）
     */
    @Test
    public void test6(){
        LocalTime now = LocalTime.now();
        System.out.println(now);
    }

    /**
     * 7.时间的小时数增加
     */
    @Test
    public void test7(){
        LocalTime now = LocalTime.now();
        //增加小时方法一
        LocalTime newTime1 = now.plusHours(1);

        //增加小时方法二
        LocalTime newTime2 = now.plus(Duration.ofHours(2));
        System.out.println(newTime1);
        System.out.println(newTime2);
    }

    /**
     * 8.获取一周后的日期
     *
     * Period和Duration类的区别：两个类表示时间量或两个日期之间的差，
     *          两者之间的差异为：Period基于日期值，而Duration基于时间值。
     */
    @Test
    public void test8(){
        LocalDate now = LocalDate.now();

        LocalDate newDate1 = now.plusWeeks(1);
        LocalDate newDate2 = now.plus(Period.ofWeeks(1));
        LocalDate newDate3 = now.plus(1, ChronoUnit.WEEKS);

        System.out.println(newDate1);
        System.out.println(newDate2);
        System.out.println(newDate3);

    }

    /**
     * 9.一年前后的日期
     */
    @Test
    public void test9(){
        LocalDate now = LocalDate.now();
        LocalDate lastYearDate = now.minus(1, ChronoUnit.YEARS);
        LocalDate nextYearDate = now.plus(1, ChronoUnit.YEARS);

        System.out.println("今年日期：" + now);
        System.out.println("去年的日期：" + lastYearDate);
        System.out.println("明年的日期：" + nextYearDate);
    }

    /**
     * 10.使用时钟
     * java8自带了Clock类，可以用来获取某个时区下（所以对时区是敏感的）当前的瞬时时间、日期。
     * 用来代替System.currentTimelnMillis()与TimeZone.getDefault()方法
     */
    @Test
    public void test10(){
        //返回当前时间根据您的系统时钟和UTC
        Clock clock = Clock.systemUTC();
        System.out.println("Clock: " + clock);

        Clock.systemDefaultZone();
        System.out.println("Clock: " + clock);

        //当前毫秒
        System.out.println(clock.millis());
    }

    /**
     * 11.判断某个日期在另一个日期的前面还是后面
     */
    @Test
    public void test11(){
        LocalDate now = LocalDate.now();
        LocalDate tommorow = now.plus(1, ChronoUnit.DAYS);

        System.out.println("今天日期：" + now);
        System.out.println("明天的日期：" + tommorow);

        System.out.println("日期 " + tommorow + " 在 " + now + " 之前？ " + tommorow.isBefore(now));
        System.out.println("日期 " + tommorow + " 在 " + now + " 之后？ " + tommorow.isAfter(now));
    }

    /**
     * 12.处理不同的时区
     *
     * java8中不仅将日期和时间进行了分离，同时还有时区。比如ZonId代表的是某个特定时区，ZonedDateTime代表带时区的时间，
     * 等同于以前的GregorianCalendar类。使用该类，可以将本地时间转换成另一个时区中的对应时间。
     */
    @Test
    public void test12(){
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.of(ZoneId.SHORT_IDS.get("ACT"));
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localDateTime,zone);
        System.out.println("现在时区的时间和在特定时区的时间：" + dateAndTimeInNewYork);
    }

    /**
     * 13.年月类
     */
    @Test
    public void test13(){
        YearMonth now = YearMonth.now();
        System.out.println(now + " 有 " + now.lengthOfMonth() + " 天");

        YearMonth newYearMonth = YearMonth.of(2018, Month.FEBRUARY);
        System.out.println(newYearMonth);
    }

    /**
     * 14.检查闰年
     *
     * LocalDate类由一个isLeapYear()方法来返回当前LocalDate对应的那年是否是闰年
     */
    @Test
    public void test14(){
//        LocalDate now = LocalDate.now();
        LocalDate now = LocalDate.of(2016,1,1);
        System.out.println(now + " 是否是闰年？ " + now.isLeapYear());
    }

    /**
     * 15.两个日期之间包含多少天，多少月
     *
     * Duration: 计算两个“时间”之间的间隔
     * Period： 计算两个“日期”之间的间隔
     *
     * 计算两个日期之间包含多少天、周、月、年。可以用java.time.Period类完成该功能。
     * 下面例子中将计算日期与将来的日期之间一共有几个月
     */
    @Test
    public void test15(){
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2019, 9, 28);
        System.out.println(date1);
        System.out.println(date2);

        Period period = Period.between(date2, date1);
        System.out.println("两个日期相差了月数：" + period.getMonths());

        System.out.println("==============================分隔符===========================");

        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.of(13, 23, 12);

        Duration duration = Duration.between(time2, time1);
        System.out.println("相隔小时数：" + duration.toHours());
    }

    /**
     * 16.带时区的日期和时间
     *
     * 在java8中，可以使用ZoneOffset来代表某个时区，可以使用它的静态方法ZoneOffset.of()方法来获取对应的时区，
     * 只要获得了这个偏移量，就可以用这个偏移量和LocalDateTime创建一个新的OffsetDateTime
     */
    @Test
    public void test16(){
        LocalDateTime now = LocalDateTime.now();
        ZoneOffset offset = ZoneOffset.of("+05:25");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(now, offset);
        System.out.println("日期和时间在时区上的偏移时间：" + zonedDateTime);
    }
    /**
     * 17.获取当前时间戳
     *
     * 时间戳（以Unix元年：1970年1月1日00:00:00到某个时间之间的毫秒值)
     *
     * Instant类由一个静态的工厂方法now()可以返回当前时间戳
     */
    @Test
    public void test17(){
        //可以看到，当前时间戳是包含日期和时间的，与java.util.Date很类似，
        // 事实上Instant就是java8以前的Date，可以使用这个两个类中的方法在这两个类型之间进行转换，
        // 比如Date.from(Instant)就是用来把Instant转换成java.util.date的，而Date。toInstant()就是将Date转换成Instant的

        //默认UTC时区，格林尼治时间
        Instant instant = Instant.now();
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        System.out.println(timestamp.getTime());
        System.out.println("当前的时间戳：" + instant);

        //获取时间戳的毫秒
        System.out.println("时间戳毫秒: " + instant.toEpochMilli());

        //对时间戳进行运算
        Instant instant1 = Instant.ofEpochSecond(60);
        System.out.println(instant1);
    }
    /**
     * 18.对自定义日期进行解析/格式化
     */
    @Test
    public void test18(){
        String myDate = "20191007";
        LocalDate localDate = LocalDate.parse(myDate, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);
    }
    /**
     * 19.使用自定义的格式器来解析日期
     */
    @Test
    public void test19(){
        String myTime = "15 13 14";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH mm ss");
        LocalTime localTime = LocalTime.parse(myTime, formatter);
        System.out.println(localTime);
    }
    /**
     * 20.对日期进行格式化，转换成字符串
     */
    @Test
    public void test20(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(formatter);
        System.out.println(format);
    }
}
