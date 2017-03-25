package com.tangcheng.demo.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MyWorld on 2016/8/13.
 */
@Service
@ConditionalOnProperty(name = "quartz.enabled", havingValue = "true")//根据条件决定是否实例化这个Bean，只能用在Bean上
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
