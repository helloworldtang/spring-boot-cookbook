package com.tangcheng.learning.service.lock.impl;

import com.tangcheng.learning.service.lock.DistributeLockService;
import com.tangcheng.learning.web.dto.req.DistributeLockTestReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
@SpringBootTest
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
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for (int i = 0; i < size; i++) {
            executorService.submit(() -> distributeLockService.mayBeMultiRepeatRequest(1L, new DistributeLockTestReq("nameValue")));
        }
        executorService.awaitTermination(5, TimeUnit.MINUTES);
        executorService.shutdown();
    }


}