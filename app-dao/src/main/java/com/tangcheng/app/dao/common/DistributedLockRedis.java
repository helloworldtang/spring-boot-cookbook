package com.tangcheng.app.dao.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class DistributedLockRedis implements DistributedLock {
    /**
     * distributed:lock:redis:biz:{bizName}
     */
    private static final String DISTRIBUTED_LOCK_REDIS_KEY = "distributed:lock:redis:id:{}";

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    @Override
    public boolean acquireLockWithTimeout(String keyWithBizId, int maxWaitTime) {

    }

    @Override
    public boolean acquireLockWithTimeout(String keyWithBizId, int acquireTimeout, int lockTimeout) {
        String key = MessageFormat.format(DISTRIBUTED_LOCK_REDIS_KEY, keyWithBizId);

        long end = System.currentTimeMillis() + (acquireTimeout * 1000);
        BoundValueOperations<String, Integer> valueOps = redisTemplate.boundValueOps(key);
        while (System.currentTimeMillis() < end) {
            if (valueOps.setNX(key, lockTimeout, Duration.ofSeconds(lockTimeout))){
                //只有获取到锁,return true
                log.info("key:{}, get lock.", key);
                return true;
            }
            try {
                log.info("try to get lock. key:{}", key);
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException ie) {
                log.warn("lock is Interrupted . key:{}", key);
                break;
            }
        }
        return false;
    }

    @Override
    public void releaseLock(String keyWithBizId) {

    }
}
