package com.tangcheng.learning.time;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.*;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class JavaUtilDateTest {
    public static void test1(Date d1, Date d2) {

        // 毫秒ms
        long diff = d2.getTime() - d1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        System.out.print("时间相差：");
        System.out.print(diffDays + " 天 ");
        System.out.print(diffHours + " 小时 ");
        System.out.print(diffMinutes + " 分钟 ");
        System.out.print(diffSeconds + " 秒.");
        System.out.println();
    }

    public static void test2(Date d1, Date d2) throws ParseException {

        DateTime dt1 = new DateTime(d1);
        DateTime dt2 = new DateTime(d2);
        System.out.print("时间相差：");
        System.out.print(Days.daysBetween(dt1, dt2).getDays() + " 天 ");
        System.out.print(Hours.hoursBetween(dt1, dt2).getHours() % 24 + " 小时 ");
        System.out.print(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + " 分钟 ");
        System.out.print(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60 + " 秒.");
        System.out.println();
    }

    public static void test3(Date d1, Date d2) {
        Interval interval = new Interval(d1.getTime(), d2.getTime());
        Period p = interval.toPeriod();
        System.out.println("时间相差：" + p.getDays() + " 天 " + p.getHours() + " 小时 " + p.getMinutes() + " 分钟" + p.getSeconds() + " 秒");
    }

    public static void main(String[] args) throws ParseException {
        String dateStart = "2017-08-12 16:29:58";
        String dateStop = "2017-08-13 16:31:48";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = format.parse(dateStart);
        Date d2 = format.parse(dateStop);
        test1(d1, d2);
        test2(d1, d2);
        test3(d1, d2);
    }

    /**
     * 当前对象代表的时间 超前 时，返回1
     * 当前对象代表的时间 与被比较的相等时，返回0
     * 当前对象代表的时间 是过去的时间 时，返回-1
     */
    @Test
    public void compareDateTest() {
        Date after5Minutes = LocalDateTime.now().plusMinutes(5).toDate();
        Date before5Minutes = LocalDateTime.now().minusMinutes(5).toDate();
        Date now = LocalDateTime.now().toDate();
        log.info("after5Minutes.compareTo(now):{}", after5Minutes.compareTo(now));
        log.info("before5Minutes.compareTo(now):{}", before5Minutes.compareTo(now));
        log.info("now.compareTo(now):{}", now.compareTo(now));
    }
}