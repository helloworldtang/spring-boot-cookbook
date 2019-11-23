package com.tangcheng.lock.service;

import com.tangcheng.lock.domain.req.DistributeLockTestReq;

import java.util.concurrent.CountDownLatch;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/17/2018 2:24 AM
 */
public interface DistributeLockService {

    void mayBeMultiRepeatRequest(Integer id, DistributeLockTestReq req, CountDownLatch latch);

}
