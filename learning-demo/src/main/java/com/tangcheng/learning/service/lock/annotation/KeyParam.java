package com.tangcheng.learning.service.lock.annotation;

import java.lang.annotation.*;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/16/2018 11:47 PM
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface KeyParam {
    /**
     * 标识分布式锁的key的一部分
     *
     * @return 标识分布式锁的key的一部分
     */
    String value() default "";
}
