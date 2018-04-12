package com.tangcheng.learning.joda;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.*;
import org.junit.Test;

/**
 * @author tangcheng
 * 2018/04/12
 */
@Slf4j
public class JodaTest {

    @Test
    public void duringHoursMinutes() {
        LocalDateTime end = new LocalDateTime(LocalDate.now().plusDays(1).toDate());
        LocalDateTime now = LocalDateTime.now();
        String pattern = "yyyy-MM-dd HH:mm:ss ssss";
        log.info("end:{}", end.toString(pattern));
        log.info("now:{}", now.toString(pattern));
        long secondsOrigin = (end.toDate().getTime() - now.toDate().getTime()) / 1000;
        long minutesOrigin = secondsOrigin / 60;
        long hourOrigin = minutesOrigin / 60;

        log.info("origin:hours:{},minutes:{},seconds:{}", hourOrigin, minutesOrigin, secondsOrigin);
        log.info("hours:{},minutes:{},seconds:{}", Hours.hoursBetween(now, end).getHours(), Minutes.minutesBetween(now, end).getMinutes(), Seconds.secondsBetween(now, end).getSeconds());
    }


}
