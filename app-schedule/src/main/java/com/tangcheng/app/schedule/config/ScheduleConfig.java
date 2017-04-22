package com.tangcheng.app.schedule.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by MyWorld on 2016/9/23.
 */
@Configuration
public class ScheduleConfig {
    /**
     * 解决Spring Boot启动时下面的报错：
     * 2016-09-23 23:28:07.241 DEBUG [com.Application.main()][org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor] Could not find default TaskScheduler bean
     * org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [org.springframework.scheduling.TaskScheduler] is defined
     * at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:372) ~[spring-beans-4.2.0.RC1.jar:4.2.0.RC1]
     * 注册下面的bean后，报错消失：
     * 2016-09-23 23:37:27.286 DEBUG [com.Application.main()][org.springframework.beans.factory.support.DefaultListableBeanFactory] Returning cached instance of singleton bean 'taskScheduler'
     * 2016-09-23 23:37:27.290 INFO  [pool-1-thread-1][ScheduledTaskService] fixRate : 2016-09-23 23:37:27 290
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(TaskScheduler.class)
    public TaskScheduler taskScheduler() {
        ScheduledExecutorService localExecutor = Executors.newSingleThreadScheduledExecutor();
        return new ConcurrentTaskScheduler(localExecutor);
    }
}
