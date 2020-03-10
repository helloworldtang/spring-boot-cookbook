package com.tangcheng.learning.validation.method.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @author: cheng.tang
 * @date: 2020/3/10
 * @see
 * @since
 */
@Configuration
@ComponentScan(basePackages = "com.tangcheng.learning.validation.method")
public class MethodValidationConfig {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
