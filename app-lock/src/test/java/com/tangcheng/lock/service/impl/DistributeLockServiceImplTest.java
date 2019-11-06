package com.tangcheng.lock.service.impl;

import com.tangcheng.AppLockApplicationTest;
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

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/17/2018 2:31 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppLockApplicationTest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DistributeLockServiceImplTest {

    @Autowired
    private DistributeLockService distributeLockService;

    /**
     * 共10个任务
     * 每个任务需要1s完成
     * 最大等待时间3s
     * 因此最多只有3个线程拿到锁
     * <p>
     * 2018-06-17 03:20:07.137 DEBUG 1036 --- [pool-2-thread-3] c.t.l.s.l.aspect.DistributedLockAspect   : success to get distribute lock .key->dl:id:1:name:dl
     * 2018-06-17 03:20:08.155 DEBUG 1036 --- [pool-2-thread-7] c.t.l.s.l.aspect.DistributedLockAspect   : success to get distribute lock .key->dl:id:1:name:dl
     * 2018-06-17 03:20:09.162 DEBUG 1036 --- [pool-2-thread-4] c.t.l.s.l.aspect.DistributedLockAspect   : success to get distribute lock .key->dl:id:1:name:dl
     *
     * @throws InterruptedException
     */
    @Test
    public void mayBeMultiRepeatRequest() throws InterruptedException {
        int size = 10;
        CountDownLatch latch = new CountDownLatch(size);
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> distributeLockService.mayBeMultiRepeatRequest(1L, new DistributeLockTestReq("nameValue"), latch));
        }
        latch.await();
        executorService.shutdown();
    }


}