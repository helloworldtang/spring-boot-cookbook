package com.tangcheng.lock.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;


/**
 * @author cheng.tang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DistributedLock {

    /**
     * 分布式锁key的前缀
     *
     * @return 分布式锁key的前缀
     */
    String prefix() default "dl";

    /**
     * Key的分隔符
     * eg: dl:user:1
     *
     * @return
     */
    String delimiter() default ":";

    /**
     * 获得锁后，自动释放锁的时间，默认为60秒
     * tips:
     * 设定成业务处理的最大时长
     *
     * @return 获得锁后，自动释放锁的时间
     */
    int expireTime() default 60;

    /**
     * 排队等待的最大时间
     * tips:
     * 这项配置主要考虑到这个场景：想让重复请求快速失败
     * （1）让晚到的重复的请求快速失败，不占用资源
     * <p>
     * 如果设置为0,在要求幂等的场景会使用到
     *
     * @return 排队等待的最大时间
     */
    int waitTime() default 3;

    /**
     * 锁和排队等待过期时间的单位，默认秒。不建议配置
     * tips:
     * 感觉这个选项可提高可读性，但也容易被误用。
     * 是不是配置成枚举更好呢
     *
     * @return 锁和排队等待过期时间的单位，默认秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 自旋等待参数的配置
     *
     * @return
     */
    SpinWaitTimeParam spinWaitTimeParam() default @SpinWaitTimeParam();

}
