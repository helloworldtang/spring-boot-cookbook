package com.tangcheng.app.dao.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-25  17:06
 */
@Service
public class DistributedLockRedis {

    public static final Logger LOGGER = LoggerFactory.getLogger(DistributedLockRedis.class);

    public static final String DISTRIBUTED_LOCK_REDIS_KEY = "distributed:lock:redis:";

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    /**
     * 由于Redis是单线程模型，命令操作原子性，所以利用这个特性可以很容易的实现分布式锁。
     * 获得一个锁
     *
     * @param bizName     业务名
     * @param lockTimeout 线程占用锁的时间
     * @param unit        单位
     * @throws InterruptedException 锁可以被中断
     */
    public void lock(String bizName, int lockTimeout, TimeUnit unit) throws InterruptedException {
//        redisTemplate.getConnectionFactory().getConnection().setNX()
        String redisKey;
        if (StringUtils.isBlank(bizName)) {
            LOGGER.warn("has no bizName. is not recommended!");
            redisKey = DISTRIBUTED_LOCK_REDIS_KEY;
        } else {
            redisKey = DISTRIBUTED_LOCK_REDIS_KEY + bizName.trim();
        }
        BoundValueOperations<String, Long> valueOps = redisTemplate.boundValueOps(redisKey);
        while (true) {
            // https://redis.io/commands/setnx
            long currentTimeMillis = System.currentTimeMillis();
            long releaseLockTime = currentTimeMillis + unit.toMillis(lockTimeout) + 1;
            //这两个if else不能混写，因为多个相同类型的线程竞争锁时,在锁超时时，设置的超时时间是一样的
            if (valueOps.setIfAbsent(releaseLockTime)) {
                /**
                 * 第一次获取锁
                 */
                redisTemplate.expire(redisKey, lockTimeout, unit);
                return;
            } else if (currentTimeMillis > valueOps.get()) {
                /**
                 * 锁已经超时
                 */
                //如果其它线程占用锁，再重新设置的时间和原来时间的时间差，可以忽略
                Long lockCurrentValue = valueOps.getAndSet(releaseLockTime);
                //如果当前时间小于LockKey存放的时间，说明已经有其它线程加锁
                if (currentTimeMillis > lockCurrentValue) {
                    redisTemplate.expire(redisKey, lockTimeout, unit);
                    return;
                }
            } else {
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(100, 1000));
            }

        }

    }


}
