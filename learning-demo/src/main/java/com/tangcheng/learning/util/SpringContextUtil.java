package com.tangcheng.learning.util;

import com.tangcheng.learning.init.spring.SpringInitBean;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * (1)调用 BeanNameAware 的 setBeanName 方法
 * (2)调用 BeanFactoryAware 的 setBeanFactory
 * (3)调用 ApplicationContextAware 的 setAppl
 * (4)调用 InitializingBean 的 afterPropertie
 * (5)调用 BeanPostProcessor 的 postProcessBe
 * (6)调用 BeanPostProcessor 的 postProcessAf
 * Bean 初始化完成，可以被使用
 * 容器关闭前，调用 DisposableBean 的 destroy 方法
 *
 * @author: cheng.tang
 * @date: 2020/8/7
 * @see SpringInitBean
 * @see
 * @since
 */
@Component("troubleShootingTool")
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取Bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name以及clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    public static String getActiveProfile() {
        Environment environment = getApplicationContext().getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        if (ArrayUtils.isEmpty(activeProfiles)) {
            return "default[activeProfiles is empty]";
        }
        return activeProfiles[0];
    }


}
