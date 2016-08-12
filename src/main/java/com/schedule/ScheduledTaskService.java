package com.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MyWorld on 2016/8/13.
 */
@Service
public class ScheduledTaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTaskService.class);
    private DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss SSS");

    @Scheduled(fixedRate = 6000)
    public void fixRate() {
        LOGGER.info("fixRate : {}", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 50 * ? * *")
    public void specialTime() {
        LOGGER.info("cron {} ", dateFormat.format(new Date()));
    }

}
