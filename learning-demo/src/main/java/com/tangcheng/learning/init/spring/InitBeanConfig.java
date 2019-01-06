package com.tangcheng.learning.init.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 1/6/2019 11:00 AM
 */
@Configuration
public class InitBeanConfig {

    @Bean(initMethod = "initMethod")
    public InitBean initBean() {
        return new InitBean();
    }

}
