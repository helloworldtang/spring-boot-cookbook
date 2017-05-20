package com.tangcheng.app.core.aop;

import java.lang.annotation.*;

/**
 * Created by tang.cheng on 2016/12/2.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLogAnnotation {
    /**
     * 业务名
     *
     * @return
     */
    String desc() default "";
}
