package com.tangcheng.component.log.aop.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: cheng.tang
 * @Date: 2023/7/24
 * @Description: spring-boot-cookbook
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogReqBizParameters {

    @AliasFor("bizKey")
    String value() default "";

    @AliasFor("value")
    String bizKey() default "";

}
