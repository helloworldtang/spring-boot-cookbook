package com.tangcheng.demo.condition;

import com.tangcheng.demo.condition.biz.WindowsListService;
import com.tangcheng.demo.condition.biz.ListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tang.cheng on 2016/11/25.
 */
@Configuration
@ComponentScan("com.demo.condition")
public class ConditionConfig {
    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowsListService() {
        return new WindowsListService();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linuxListService() {
        return new WindowsListService();
    }
}
