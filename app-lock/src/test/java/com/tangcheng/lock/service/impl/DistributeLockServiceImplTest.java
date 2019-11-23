package com.tangcheng.lock.service.impl;

import com.tangcheng.lock.AppLockApplication;
import com.tangcheng.lock.domain.req.DistributeLockTestReq;
import com.tangcheng.lock.service.DistributeLockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/17/2018 2:31 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppLockApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DistributeLockServiceImplTest {

    @Autowired
    private DistributeLockService distributeLockService;

    /**
     * 共10个任务
     * 每个任务需要1s完成
     * 最大等待时间3s
     * 因此最多只有3个线程拿到锁
     * <p>
     * 2019-11-23 13:54:44.317 DEBUG 2660 --- [pool-1-thread-2] c.t.lock.aspect.DistributedLockAspect    : success to get distribute lock .key->dl:id:1:name:nameValue
     * 2019-11-23 13:54:45.324 DEBUG 2660 --- [pool-1-thread-9] c.t.lock.aspect.DistributedLockAspect    : success to get distribute lock .key->dl:id:1:name:nameValue
     * 2019-11-23 13:54:46.327 DEBUG 2660 --- [ool-1-thread-10] c.t.lock.aspect.DistributedLockAspect    : success to get distribute lock .key->dl:id:1:name:nameValue
     * 2019-11-23 13:54:47.331 DEBUG 2660 --- [pool-1-thread-6] c.t.lock.aspect.DistributedLockAspect    : success to get distribute lock .key->dl:id:1:name:nameValue
     *
     * @throws InterruptedException
     */
    @Test
    public void mayBeMultiRepeatRequest() throws InterruptedException {
        int size = 10;
        CountDownLatch latch = new CountDownLatch(size);
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> {
                DistributeLockTestReq req = new DistributeLockTestReq();
                req.setName("tangcheng");
                req.setAge((short) 30);
                req.setAlone(false);
                distributeLockService.mayBeMultiRepeatRequest(1, req, latch);
            });
        }
        latch.await(20, TimeUnit.SECONDS);
        executorService.shutdown();
    }


}