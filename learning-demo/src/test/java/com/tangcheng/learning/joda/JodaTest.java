package com.tangcheng.learning.joda;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tangcheng
 * 2018/04/12
 */
@Slf4j
public class JodaTest {

    /**
     * 13:28:51.527 [main] INFO com.tangcheng.learning.joda.JodaTest - end:2018-04-13 00:00:00 000
     * 13:28:51.531 [main] INFO com.tangcheng.learning.joda.JodaTest - now:2018-04-12 13:28:51 051
     * 13:28:51.532 [main] INFO com.tangcheng.learning.joda.JodaTest - origin:hours:10,minutes:631,seconds:37868
     * 13:28:51.541 [main] INFO com.tangcheng.learning.joda.JodaTest - hours:10,minutes:631,seconds:37868
     */
    @Test
    public void duringHoursMinutes() {
        LocalDateTime end = new LocalDateTime(LocalDate.now().plusDays(1).toDate());
        LocalDateTime now = LocalDateTime.now();
        String pattern = "yyyy-MM-dd HH:mm:ss sss";
        log.info("end:{}", end.toString(pattern));
        log.info("now:{}", now.toString(pattern));
        long secondsOrigin = (end.toDate().getTime() - now.toDate().getTime()) / 1000;
        long minutesOrigin = secondsOrigin / 60;
        long hourOrigin = minutesOrigin / 60;

        log.info("origin:hours:{},minutes:{},seconds:{}", hourOrigin, minutesOrigin, secondsOrigin);
        log.info("hours:{},minutes:{},seconds:{}", Hours.hoursBetween(now, end).getHours(), Minutes.minutesBetween(now, end).getMinutes(), Seconds.secondsBetween(now, end).getSeconds());
    }


    @Test
    public void should_return_diff_hour_day_when_use_JODA_API() {
        int dayNum = 2;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plusDays(dayNum);
        int expectedDayNum = Days.daysBetween(now, end).getDays();
        System.out.println(expectedDayNum);
        assertThat(expectedDayNum).isEqualTo(dayNum);
        int expectedHours = Hours.hoursBetween(now, end).getHours();
        assertThat(expectedHours).isEqualTo(dayNum * 24);
    }

    /**
     * java.lang.IllegalArgumentException: Invalid format: "2021-01-16 00:00:00" is malformed at " 00:00:00"
     * at org.joda.time.format.DateTimeFormatter.parseLocalDateTime(DateTimeFormatter.java:900)
     * at org.joda.time.LocalDateTime.parse(LocalDateTime.java:168)
     * at org.joda.time.LocalDateTime.parse(LocalDateTime.java:157)
     */
    @Test(expected = IllegalArgumentException.class)
    public void given_StringDateTime_when_no_locale_then_IllegalArgumentException() {
        LocalDateTime.parse("2021-01-16 00:00:00");
    }


    /**
     * Expecting:
     * <2021-01-16T00:00:00.000 (java.util.Date)>
     * to be equal to:
     * <2021-02-16T00:00:00.922 (java.util.Date)>
     * but was not.
     * Expected :2021-02-16T00:00:00.922 (java.util.Date)
     * Actual   :2021-01-16T00:00:00.000 (java.util.Date)
     * <p>
     * 初始值
     * 代码如下，值得指出的是由于我们的时区设置是GMT+8,所以打印格林威治时间得到的是1970-01-01 08:00:00.
     * Calendar cal = Calendar.getInstance（）；//得到当前时间
     * cal.setTimeInMillis（0）；//转换成格林威治时间 1970-01-01T08:00:00.000
     */
    @Test
    public void given_StringDateTime_when_no_locale_then_LocalDateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.JANUARY, 16, 0, 0, 0);
        /**
         * 原因是Calendar.getInstance()时候，会给Calendar赋值为当前时间的毫秒值，调用set方法会根据参数对年、月、日、时、分、秒对应的值进行替换。
         * 然而无法对毫秒值进行替换，所以毫秒值是创建Calendar对象时的毫秒值。
         */
        calendar.set(Calendar.MILLISECOND, 0);
        Date expected = calendar.getTime();
        String dateString = "2021-01-16 00:00:00";
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").withLocale(Locale.SIMPLIFIED_CHINESE));
        Date actual = localDateTime.toDate();
        System.out.println(actual);
        assertThat(actual).isEqualTo(expected);
    }


}
