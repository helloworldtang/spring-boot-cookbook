package com.tangcheng.component.cost.aop.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeCostStatistics {
    /**
     * 业务标识，可以作为一个搜索关键字
     *
     * @return
     */
    String bizFlag();

    /**
     * 可选项。
     * 在bizFlag的基础上提供更细粒度的日志标识。
     * 譬如 更新某个id的耗时。 这个id就可以作为key的一部分
     * key支持SpringEL表达式
     *
     * @return
     */
    String key() default "";

    /**
     * 输出耗时的单位。默认是毫秒
     * 一些耗时任务，可以指定为分钟
     *
     * @return
     * @see TimeUnit
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

}
