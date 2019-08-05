package com.tangcheng.learning.web.dto.req;

import com.tangcheng.learning.service.lock.annotation.KeyParam;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/17/2018 2:28 AM
 */
@Data
@AllArgsConstructor
public class DistributeLockTestReq {
    @KeyParam("name")
    private String name;
}
