package com.schedule;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by MyWorld on 2016/8/13.
 */
@Configuration
@ComponentScan("com.schedule")
@EnableScheduling
public class SchedulerConfig {
}
