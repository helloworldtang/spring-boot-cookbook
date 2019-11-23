package com.tangcheng.lock.service.impl;

import com.tangcheng.lock.annotation.DistributedLock;
import com.tangcheng.lock.annotation.KeyParam;
import com.tangcheng.lock.annotation.SpinWaitTimeParam;
import com.tangcheng.lock.domain.req.DistributeLockTestReq;
import com.tangcheng.lock.service.DistributeLockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/17/2018 2:25 AM
 */
@Slf4j
@Service
public class DistributeLockServiceImpl implements DistributeLockService {


    @Override
    @DistributedLock(spinWaitTimeParam = @SpinWaitTimeParam(spinWaitTime = 10))
    public void mayBeMultiRepeatRequest(@KeyParam("id") @RequestParam Integer id, DistributeLockTestReq req, CountDownLatch latch) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("id:{},req:{}", id, req);
        latch.countDown();
    }


}
