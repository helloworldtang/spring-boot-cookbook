package com.tangcheng.learning.init.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * Created by tangcheng on 2017/1/5.
 */
@Slf4j
public class SpringInitBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {

    /**
     * 1.Spring对bean进行实例化
     * 2.Spring将值和bean的引用注入到bean对应的属性中
     * 3.如果bean实现了BeanNameAware接口，Spring将bean的ID传递给setBeanName()方法；
     *
     * @param s
     */
    @Override
    public void setBeanName(String s) {
        log.info("Step-3调用。BeanNameAware.setBeanName {}", s);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("Step-4调用。BeanFactoryAware.setBeanFactory {}", beanFactory);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("Step-5调用。ApplicationContextAware.setApplicationContext {}", applicationContext);
    }


    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        log.info("Step-6调用。BeanPostProcessor.postProcessBeforeInitialization o={},s={}", o, s);
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Step-7调用。InitializingBean.afterPropertiesSet");
        log.info("Step-7调用。类似地，如果bean使用init-method声明了初始化方法，该方法也会被调用");
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        log.info("Step-8调用。BeanPostProcessor.postProcessAfterInitialization o={},s={}", o, s);
        return null;
    }

    @Override
    public void destroy() throws Exception {
        log.info("Step-10调用。DisposableBean.destroy");
        log.info("Step-10调用。同样，如果bean使用destroy-method声明了销毁方法，该方法也会被调用");
    }

    public SpringInitBean() {
        log.info("begin to execute Constructor Function");
    }

    public void initMethod() {
        log.info("begin to execute initMethod");
    }

    public void destroyMethod() {
        log.info("begin to execute destroyMethod");
    }


    @PostConstruct
    public void postConstructMethod() {
        log.info("begin to execute @PostConstruct");
    }


}
