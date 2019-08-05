package com.tangcheng.learning.service.lock.key;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * spring-boot-cookbook
 * 分布式锁key的生成器
 *
 * @author tangcheng
 * @date 6/16/2018 11:51 PM
 */
public interface KeyGenerator {
    /**
     * 根据AOP参数，生成指定分布式锁的Key
     *
     * @param proceedingJoinPoint
     * @return 缓存的key
     */
    String getLockKey(ProceedingJoinPoint proceedingJoinPoint);

}
