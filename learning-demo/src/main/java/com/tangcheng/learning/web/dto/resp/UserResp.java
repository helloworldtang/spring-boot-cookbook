package com.tangcheng.learning.web.dto.resp;

import com.tangcheng.learning.web.dto.req.UserReq;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 10/2/2018 12:37 PM
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户信息UserResp")
public class UserResp extends UserReq {
}
