package com.tangcheng.learning.service.lock;

import com.tangcheng.learning.service.lock.annotation.DistributedLock;
import com.tangcheng.learning.service.lock.annotation.KeyParam;
import com.tangcheng.learning.web.dto.req.DistributeLockTestReq;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/17/2018 2:24 AM
 */
public interface DistributeLockService {


    @DistributedLock
    void mayBeMultiRepeatRequest(@KeyParam("id") @RequestParam Long id, DistributeLockTestReq req);
}
