package com.tangcheng.learning.init.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

/**
 * afterPropertiesSet 和init-method之间的执行顺序是
 * afterPropertiesSet 先执行，init-method 后执行。
 * 从BeanPostProcessor的作用，可以看出
 * 最先执行的是postProcessBeforeInitialization，
 * 然后是afterPropertiesSet，
 * 然后是init-method，
 * 然后是postProcessAfterInitialization
 * <p>
 * Created by tangcheng on 2017/1/5.
 */
@Slf4j
public class SpringInitBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {

    /**
     * 1.Spring对bean进行实例化
     * 2.Spring将值和bean的引用注入到bean对应的属性中
     * 3.如果bean实现了BeanNameAware接口，Spring将bean的ID传递给setBeanName()方法；
     *
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        log.info("Step-3调用。BeanNameAware.setBeanName {}", name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("Step-4调用。BeanFactoryAware.setBeanFactory {}", beanFactory);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("Step-5调用。ApplicationContextAware.setApplicationContext {}", applicationContext);
    }

    /**
     * BeanPostProcessor，针对所有Spring上下文中所有的bean，可以在配置文档 applicationContext.xml 中配置一个 BeanPostProcessor ，然后对所有的bean进行一个初始化之前和之后的代理。
     * BeanPostProcessor 接口中有两个方法： postProcessBeforeInitialization 和 postProcessAfterInitialization 。
     * postProcessBeforeInitialization 方法在bean初始化之前执行，
     * postProcessAfterInitialization 方法在bean初始化之后执行。
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("Step-6调用。BeanPostProcessor.postProcessBeforeInitialization bean={},beanName={}", bean.getClass().getCanonicalName(), beanName);
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Step-7调用。InitializingBean.afterPropertiesSet");
        log.info("Step-7调用。类似地，如果bean使用init-method声明了初始化方法，该方法也会被调用");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("Step-8调用。BeanPostProcessor.postProcessAfterInitialization bean={},beanName={}", bean, beanName);
        return bean;
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
