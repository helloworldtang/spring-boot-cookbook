package com.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by tangcheng on 2017/1/5.
 */
public class InitTestBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitTestBean.class);

    @Override
    public void setBeanName(String s) {
        LOGGER.info("BeanNameAware.setBeanName {}", s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        LOGGER.info("BeanFactoryAware.setBeanFactory {}", beanFactory);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOGGER.info("ApplicationContextAware.setApplicationContext {}", applicationContext);
    }


    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        LOGGER.info("BeanPostProcessor.postProcessBeforeInitialization o={},s={}", o, s);
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("InitializingBean.afterPropertiesSet");
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        LOGGER.info("BeanPostProcessor.postProcessAfterInitialization o={},s={}", o, s);
        return null;
    }

    @Override
    public void destroy() throws Exception {
        LOGGER.info("DisposableBean.destroy");
    }
}
