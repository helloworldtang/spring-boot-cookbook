package com.demo.schedule.quartz;

import com.demo.schedule.quartz.factory.MyJobFactory;
import org.quartz.spi.JobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by MyWorld on 2016/9/12.
 */
@Configuration
@EnableScheduling
@ComponentScan("com.demo.schedule.quartz")
public class QuartzConfig {

    @Bean
    public JobFactory jobFactory() {
        return new MyJobFactory();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 延时启动
        schedulerFactoryBean.setStartupDelay(20);

        schedulerFactoryBean.setJobFactory(jobFactory);
        return schedulerFactoryBean;
    }

}
