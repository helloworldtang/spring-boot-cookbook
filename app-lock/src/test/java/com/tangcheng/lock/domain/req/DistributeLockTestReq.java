package com.tangcheng.lock.domain.req;

import com.tangcheng.lock.annotation.KeyParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/17/2018 2:28 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributeLockTestReq {

    @KeyParam("alone")
    private Boolean alone;

    @KeyParam("name")
    private String name;

    @KeyParam("age")
    private Short age;


}
