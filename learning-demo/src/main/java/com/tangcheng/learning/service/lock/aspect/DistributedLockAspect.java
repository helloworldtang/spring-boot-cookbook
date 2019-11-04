package com.tangcheng.learning.service.lock.aspect;

import com.tangcheng.learning.service.lock.annotation.DistributedLock;
import com.tangcheng.learning.service.lock.annotation.SpinWaitTimeParam;
import com.tangcheng.learning.service.lock.exception.DistributedLockException;
import com.tangcheng.learning.service.lock.key.KeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * spring-boot-cookbook
 * 基于AOP的分布式锁的主逻辑
 *
 * @author tangcheng
 * @date 6/17/2018 12:41 AM
 */
@Aspect
@Component
@Slf4j
public class DistributedLockAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private KeyGenerator keyGenerator;

    @Pointcut("@annotation(com.tangcheng.learning.service.lock.annotation.DistributedLock)")
    public void distributedLockPointCut() {
    }

    @Around("execution(public * *(..))&&distributedLockPointCut()")
    public Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock annotation = method.getAnnotation(DistributedLock.class);
        String lockKey = keyGenerator.getLockKey(proceedingJoinPoint);
        if (StringUtils.isBlank(lockKey)) {
            log.warn("lockKey is blank");
            throw new DistributedLockException("lock key must not be blank");
        }
        Boolean success = getLock(annotation, lockKey);
        if (success) {
            try {
                return proceedingJoinPoint.proceed();
            } finally {
                stringRedisTemplate.delete(lockKey);
                log.debug("release lock . key->{}", lockKey);
            }
        }
        //能走到这的，肯定是没有获取到锁，原因有两个，超过了等待时间还没有获取到
        log.warn(" 没有获取到锁 key->{}", lockKey);
        throw new DistributedLockException("没有获取到锁");
    }

    private Boolean getLock(DistributedLock annotation, String lockKey) throws InterruptedException {
        TimeUnit unit = annotation.timeUnit();
        int timeout = annotation.waitTime();
        long waitMills = TimeUnit.MILLISECONDS.convert(timeout, unit);
        Long expire = stringRedisTemplate.getExpire(lockKey);
        if (Objects.equals(expire, -1L)) {
            /**
             * 当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。 否则，以毫秒为单位，返回 key 的剩余生存时间。
             * 注意：在 Redis 2.8 以前，当 key 不存在，或者 key 没有设置剩余生存时间时，命令都返回 -1 。
             */
            log.warn("可能存在没有设置过期时间的情况 ->{}", lockKey);
            stringRedisTemplate.expire(lockKey, annotation.expireTime(), unit);
        }

        /**
         * 解决幂等的场景。waitMills会设置为0，但要走一遍
         */
        while (waitMills >= 0) {
            Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "0");
            if (success) {
                stringRedisTemplate.expire(lockKey, annotation.expireTime(), unit);
                log.debug("success to get distribute lock .key->{}", lockKey);
                return true;
            }
            SpinWaitTimeParam spinWaitTimeParam = annotation.spinWaitTimeParam();
            int spinWaitTime = spinWaitTimeParam.spinWaitTime();
            long spinWaitTimeMills = TimeUnit.MILLISECONDS.convert(spinWaitTime, spinWaitTimeParam.timeUnit());
            TimeUnit.MILLISECONDS.sleep(spinWaitTimeMills);
            waitMills = waitMills - spinWaitTime;
            log.debug("try to get lock->{}", lockKey);
        }
        log.warn("fail to ge lock .key->{}", lockKey);
        return false;
    }


}
