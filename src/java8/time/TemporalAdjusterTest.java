package java8.time;

import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.Set;

/**
 * 时间矫正器
 *
 * @Author: xjf
 * @Date: 2019/10/29 23:20
 */
public class TemporalAdjusterTest {

    /**
     * TemporalAdjuster: 时间矫正器
     */
    @Test
    public void test1(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        //指定月份
        LocalDateTime ldt = now.withDayOfMonth(2);
        System.out.println(ldt);

        //获取下一个周末
        LocalDateTime ldt2 = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt2);

        //自定义获取下一个工作日
        LocalDateTime myDateTime = LocalDateTime.of(2019, 11, 1, 10, 10, 10);
        LocalDateTime nextWorkDay = myDateTime.with((temporal -> {
            LocalDateTime localDateTime = (LocalDateTime) temporal;
            DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();

            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                //如果当前是周五，则加3天为下周一
                return localDateTime.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                //周六，加2天
                return localDateTime.plusDays(2);
            } else {
                //其他时间都加一天
                return localDateTime.plusDays(1);
            }
        }));

        System.out.println("下个工作日：" + nextWorkDay);
    }

    /**
     * 测试带时区的时间
     *
     * 带时区的意思是，首先获取对应时区的时间，然后可以使用ZonedDateTime为该时间带上时区
     * 此时区就是相对于格林尼治时间（UTC)来说的偏差，比如中国的时间会带上尾巴“+08:00”，代表UTC时区的时间加上8小时就是中国上海的时间
     */
    @Test
    public void test2(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();

        availableZoneIds.forEach(System.out::println);

        System.out.println("======================================分割符=============================");

        LocalDateTime universal = LocalDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println(universal);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);

        ZonedDateTime universal1 = ZonedDateTime.of(universal, ZoneId.of("US/Pacific"));
        System.out.println(universal1);
    }
}
