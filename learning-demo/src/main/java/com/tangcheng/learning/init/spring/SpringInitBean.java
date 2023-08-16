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

    /**
     * 这段代码是Spring框架中的一部分，定义了一个接口InitializingBean。
     * 这个接口被需要在一系列属性被设置之后执行某些操作（例如自定义初始化，或者仅仅检查所有必需的属性是否已经被设置）的Bean使用。
     * <p>
     * 接口中定义了一个方法afterPropertiesSet()。
     * 当Bean的所有属性都已经设置并且所有的BeanFactoryAware、ApplicationContextAware等已经满足后，这个方法会被调用。
     * 这个方法允许Bean实例在所有属性都已经设置的情况下进行整体配置的验证和最后的初始化。
     *
     * <p>
     * 如果在Bean的初始化过程中出现了错误（例如，未能设置一个必要的属性）或者其他任何原因导致初始化失败，那么这个方法将抛出异常。
     * <p>
     * 总的来说，这个接口提供了一种方式，让Spring框架在Bean的所有属性都已经设置后执行一些额外的初始化操作，同时也可以在Bean的初始化过程中捕获并处理任何可能出现的错误。
     *
     * @throws Exception
     */
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
