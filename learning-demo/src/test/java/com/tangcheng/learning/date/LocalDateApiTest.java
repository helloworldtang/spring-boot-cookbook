package com.tangcheng.learning.date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-19  16:05
 */
public class LocalDateApiTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(LocalDateApiTest.class);

    @Test
    public void testLocalDateTime() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        LocalDateTime parse = LocalDateTime.parse("2017-08-19 15:59:11", DateTimeFormatter.ofPattern(pattern));
        String format = parse.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LOGGER.info(format);
    }

    @Test(expected = DateTimeParseException.class)
    public void testLocalDateTimeFomatter() {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        LocalDateTime.parse("2017-8-19 15:59:11", DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void test() {
        //LocalDate,LocalTime,LocalDateTime的时间格式，严格按照ISO
        LOGGER.info(LocalDate.now().toString());//2017-07-19
        LOGGER.info(LocalTime.now().toString());//17:12:58.444
        LOGGER.info(LocalTime.now().withNano(0).toString());//17:12:58
        LOGGER.info(LocalDateTime.now().toString());//2017-07-19T17:12:58.444
        // 根据年月日取日期，12月就是12：
        Assert.assertThat(LocalDate.of(2014, 12, 25), is(LocalDate.parse("2014-12-25")));

        LOGGER.info("本月第一天（日期格式(ISO)：yyyy-MM-dd）:{}", LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
        LOGGER.info("本月第二天（日期格式(ISO)：yyyy-MM-dd）:{}", LocalDate.now().withDayOfMonth(2));
        LOGGER.info("本月最后一天（日期格式(ISO)：yyyy-MM-dd）:{}", LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));
        LOGGER.info("上月最后一天（日期格式(ISO)：yyyy-MM-dd）:{}", LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).minusDays(1));
        LOGGER.info("本月第二天（日期格式(ISO)：yyyy-MM-dd）:{}", LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).plusDays(1));
        LOGGER.info("本月第一个周一（日期格式(ISO)：yyyy-MM-dd）:{}", LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
    }

    @Test
    public void testInstant() {
        //Instant类代表的是某个时间（有点象java.util.Date），它是精确到纳秒的（而不是象旧版本的Date精确到毫秒）
        // 其内部是由两个Long字段组成，
        // 第一个部分保存的是自标准Java计算时代（就是1970年1月1日开始）到现在的秒数，
        // 第二部分保存的是纳秒数（永远不会超过999,999,999）
        //以ISO-8601输出
        // 国际标准化组织的国际标准ISO 8601是日期和时间的表示方法，全称为《数据存储和交换形式·信息交换·日期和时间的表示方法》
        // 年为4位数，月为2位数，月中的日为2位数，例如2004年5月3日可写成2004-05-03或20040503。
        // 小时、分和秒都用2位数表示，对UTC时间最后加一个大写字母Z，其他时区用实际时间加时差表示。
        // 如UTC时间下午2点30分5秒表示为14:30:05Z或143005Z，
        // 当时的北京时间表示为22:30:05+08:00或223005+0800，也可以简化成223005+08。
        // 合并表示时，要在时间前面加一大写字母T，
        // 如要表示北京时间2004年5月3日下午5点30分8秒，可以写成2004-05-03T17:30:08+08:00或20040503T173008+08。
        Instant instant = Instant.now();
        System.out.println(instant);//2017-07-23T05:35:04.431Z
        //将java.util.Date转换为Instant
        Instant fromDate = Instant.ofEpochMilli(new Date().getTime());
        System.out.println(fromDate);//2017-07-25T05:42:33.897Z
        //从字符串类型中创建Instant类型的时间
        instant = Instant.parse("2017-07-23T10:12:35Z");
        System.out.println(instant);//2017-07-23T10:12:35Z


    }

    // 01. java.util.Date --> java.time.LocalDateTime
    @Test
    public void UDateToLocalDateTime() {
        Date date = new Date();
        System.out.println("date:" + date);
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        System.out.println("localDateTime:" + localDateTime);
    }

    // 02. java.util.Date --> java.time.LocalDate
    @Test
    public void UDateToLocalDate() {
        Date date = new Date();
        System.out.println("date:" + date);
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        System.out.println("localDate:" + localDate);
    }


    // 03. java.util.Date --> java.time.LocalTime
    @Test
    public void UDateToLocalTime() {
        Date date = new Date();
        System.out.println("date:" + date);

        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println("localTime:" + localTime);
    }


    // 04. java.time.LocalDateTime --> java.util.Date
    @Test
    public void LocalDateTimeToUdate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime:" + localDateTime);

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println("date:" + date);

    }


    // 05. java.time.LocalDate --> java.util.Date
    @Test
    public void LocalDateToUdate() {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate:" + localDate);

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println("date:" + date);

    }

    // 06. java.time.LocalTime --> java.util.Date
    @Test
    public void LocalTimeToUdate() {
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime:" + localTime);

        LocalDate localDate = LocalDate.now();
        System.out.println("localDate:" + localDate);

        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        System.out.println("date:" + date);
    }


}
