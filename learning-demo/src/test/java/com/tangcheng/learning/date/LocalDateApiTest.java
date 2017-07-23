package com.tangcheng.learning.date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

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


}
