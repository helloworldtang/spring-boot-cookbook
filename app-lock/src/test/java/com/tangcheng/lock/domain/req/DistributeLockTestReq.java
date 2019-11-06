package com.tangcheng.lock.domain.req;

import com.tangcheng.lock.annotation.KeyParam;
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
