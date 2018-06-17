package com.tangcheng.learning.web.dto.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/17/2018 11:32 AM
 */
@ApiModel("用户信息")
@Data
@AllArgsConstructor
public class UserVO {

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

}
