package com.tangcheng.learning.service.lock.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author: cheng.tang
 * @date: 2019/11/4
 * @see
 * @since
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpinWaitTimeParam {

    /**
     * 竞争锁时，每隔多少时间检测一次,默认100ms
     * tips:
     * 这个时间要根据业务情况进行设定，要小于业务的平均处理时长
     * 如果业务比较耗时，可以设置的大一点，减少请求中心化服务器的频次
     *
     * @return 竞争锁时，每隔多少时间检查一下是否可以加锁
     */
    int spinWaitTime() default 100;

    /**
     * @return 自旋等待时间的单位，默认ms
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

}
