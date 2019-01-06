package com.tangcheng.learning.init.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 1/6/2019 8:12 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringInitBeanLifeCycleConfig.class)
public class SpringInitBeanLifeCycleConfigTest {

    @Test
    public void init() {
        /**
         *1.Spring对bean进行实例化
         *2.Spring将值和bean的引用注入到bean对应的属性中
         * [    main] c.t.learning.init.spring.SpringInitBean  : begin to execute Constructor Function
         * [    main] c.t.learning.init.spring.SpringInitBean  : Step-3调用。BeanNameAware.setBeanName springInitBean
         * [    main] c.t.learning.init.spring.SpringInitBean  : Step-4调用。BeanFactoryAware.setBeanFactory org.springframework.beans.factory.support.DefaultListableBeanFactory@341814d3: defining beans [org.springframework.boot.test.mock.mockito.MockitoPostProcessor$SpyPostProcessor,org.springframework.boot.test.mock.mockito.MockitoPostProcessor,org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,org.springframework.context.event.internalEventListenerProcessor,org.springframework.context.event.internalEventListenerFactory,springInitBeanLifeCycleConfig,org.springframework.boot.autoconfigure.internalCachingMetadataReaderFactory,springInitBean]; root of factory hierarchy
         * [    main] c.t.learning.init.spring.SpringInitBean  : Step-5调用。ApplicationContextAware.setApplicationContext org.springframework.web.context.support.GenericWebApplicationContext@5ab9e72c: startup date [Sun Jan 06 20:51:38 CST 2019]; root of context hierarchy
         * [    main] c.t.learning.init.spring.SpringInitBean  : begin to execute @PostConstruct
         * [    main] c.t.learning.init.spring.SpringInitBean  : Step-7调用。InitializingBean.afterPropertiesSet
         * [    main] c.t.learning.init.spring.SpringInitBean  : Step-7调用。类似地，如果bean使用init-method声明了初始化方法，该方法也会被调用
         * [    main] c.t.learning.init.spring.SpringInitBean  : begin to execute initMethod
         * [    main] c.t.learning.init.spring.SpringInitBean  : Step-6调用。BeanPostProcessor.postProcessBeforeInitialization o=org.springframework.context.event.EventListenerMethodProcessor@222eb8aa,s=org.springframework.context.event.internalEventListenerProcessor
         * [    main] c.t.learning.init.spring.SpringInitBean  : Step-6调用。BeanPostProcessor.postProcessBeforeInitialization o=org.springframework.context.event.DefaultEventListenerFactory@1852a3ff,s=org.springframework.context.event.internalEventListenerFactory
         * [    main] .l.i.s.SpringInitBeanLifeCycleConfigTest : Started SpringInitBeanLifeCycleConfigTest in 4.59 seconds (JVM running for 7.81)
         * [    main] c.t.learning.init.spring.SpringInitBean  : Step-6调用。BeanPostProcessor.postProcessBeforeInitialization o=com.tangcheng.learning.init.spring.SpringInitBeanLifeCycleConfigTest@7b84fcf8,s=com.tangcheng.learning.init.spring.SpringInitBeanLifeCycleConfigTest
         * [Thread-1] o.s.w.c.s.GenericWebApplicationContext   : Closing org.springframework.web.context.support.GenericWebApplicationContext@5ab9e72c: startup date [Sun Jan 06 20:51:38 CST 2019]; root of context hierarchy
         * [Thread-1] c.t.learning.init.spring.SpringInitBean  : Step-10调用。DisposableBean.destroy
         * [Thread-1] c.t.learning.init.spring.SpringInitBean  : Step-10调用。同样，如果bean使用destroy-method声明了销毁方法，该方法也会被调用
         * [Thread-1] c.t.learning.init.spring.SpringInitBean  : begin to execute destroyMethod
         *
         */
    }


}