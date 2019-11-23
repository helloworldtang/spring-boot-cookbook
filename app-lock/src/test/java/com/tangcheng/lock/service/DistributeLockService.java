package com.tangcheng.lock.service;

import com.tangcheng.lock.annotation.DistributedLock;
import com.tangcheng.lock.annotation.KeyParam;
import com.tangcheng.lock.annotation.SpinWaitTimeParam;
import com.tangcheng.lock.domain.req.DistributeLockTestReq;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CountDownLatch;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/17/2018 2:24 AM
 */
public interface DistributeLockService {


    @DistributedLock(spinWaitTimeParam = @SpinWaitTimeParam(spinWaitTime = 10))
    void mayBeMultiRepeatRequest(@KeyParam("id") @RequestParam Integer id, DistributeLockTestReq req, CountDownLatch latch);

}
