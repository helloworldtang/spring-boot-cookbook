package com.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by tangcheng on 2017/1/5.
 */
@Configuration
public class InitConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitConfig.class);

    @Bean
    public InitTestBean initTestBean() {
        return new InitTestBean();
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InitConfig.class);
        InitTestBean initTestBean = context.getBean(InitTestBean.class);
        initTestBean.destroy();
        LOGGER.info("Invoke destroy method not make GC recycle object.\n recycle is decided by spring self(when close): {}", initTestBean == null);
        context.close();
    }
}
