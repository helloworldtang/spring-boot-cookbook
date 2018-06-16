package com.tangcheng.learning.service.impl;

import com.tangcheng.learning.service.DistributeLockService;
import com.tangcheng.learning.service.lock.annotation.DistributedLock;
import com.tangcheng.learning.service.lock.annotation.KeyParam;
import com.tangcheng.learning.web.dto.req.DistributeLockTestReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    @DistributedLock
    public void mayBeMultiRepeatRequest(@KeyParam(name = "id") @RequestParam Long id, DistributeLockTestReq req) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("id:{},req:{}", id, req);
    }
}
