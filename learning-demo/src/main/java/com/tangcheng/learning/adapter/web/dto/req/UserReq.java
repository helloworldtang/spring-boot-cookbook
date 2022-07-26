package com.tangcheng.learning.adapter.web.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 10/2/2018 12:31 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户信息UserReq")
public class UserReq {

    @ApiModelProperty(value = "姓名", required = true, example = "dduspace")
    @NotEmpty
    @Length(max = 100, min = 2)
    private String name;

    @ApiModelProperty(value = "年龄", required = true, example = "30")
    @NotNull
    @Max(500)
    @Min(1)
    private Integer age;

}
