package com.tangcheng.learning.condition;


import com.tangcheng.learning.condition.biz.ListService;
import com.tangcheng.learning.condition.biz.WindowsListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tang.cheng on 2016/11/25.
 */
@Configuration
@ComponentScan("com.tangcheng.learning")
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
