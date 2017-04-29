package com.tangcheng.app.schedule.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tangcheng on 4/29/2017.
 */
@Configuration
public class QuartzConfig {

    @Bean
    public Scheduler scheduler(SpringJobFactory springJobFactory) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.setJobFactory(springJobFactory);
        scheduler.start();
        return scheduler;
    }


}
