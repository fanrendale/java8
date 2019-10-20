package java8.time;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;

/**
 * @Author: xjf
 * @Date: 2019/9/24 22:03
 */
public class ClockTest {

    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        //可以替代System.currentTimeMillis()
        long time = clock.millis();

        System.out.println(time);

        Instant instant = clock.instant();
        Date date = Date.from(instant);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
    }
}
