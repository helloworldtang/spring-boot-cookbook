package com.tangcheng.learning.init.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by tangcheng on 2017/1/5.
 */
@Configuration
@Slf4j
public class SpringInitBeanLifeCycleConfig {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public SpringInitBean springInitBean() {
        return new SpringInitBean();
    }


    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringInitBeanLifeCycleConfig.class);
        SpringInitBean springInitBean = context.getBean(SpringInitBean.class);
        springInitBean.destroy();
        log.info("Invoke destroy method not make GC recycle object.\n recycle is decided by spring self(when close): {}", springInitBean == null);
        context.close();
    }


}
