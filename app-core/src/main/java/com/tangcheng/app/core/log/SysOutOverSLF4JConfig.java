package com.tangcheng.app.core.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4JServletContextListener;

/**
 * Created by tang.cheng on 2017/6/1.
 */
@Configuration
public class SysOutOverSLF4JConfig {

    @Bean
    public SysOutOverSLF4JServletContextListener sysOutOverSLF4JServletContextListener() {
        return new SysOutOverSLF4JServletContextListener();
    }

}
