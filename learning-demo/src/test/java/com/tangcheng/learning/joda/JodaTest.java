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


}
