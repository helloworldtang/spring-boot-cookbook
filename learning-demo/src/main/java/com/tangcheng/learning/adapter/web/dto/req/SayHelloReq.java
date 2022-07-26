package com.tangcheng.learning.adapter.web.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author tangcheng
 * 2018/05/02
 */
@ApiModel("Swagger使用示例")
@Data
public class SayHelloReq {

    @ApiModelProperty(required = true, value = "UserId", dataType = "int", example = "123")
    @NotNull
    @Min(value = 1, message = "最小值必须大于1")
    private Integer userId;

    @ApiModelProperty(required = true, value = "内容", example = "Best Wish!")
    @NotEmpty
    private String content;

    /**
     * 普通的数组
     * example 中 数组中字符串会自动加上双引号
     */
    @ApiModelProperty(example = "[http://1.com,http://2.com]")
    @NotEmpty
    private String[] pics;

    @ApiModelProperty(example = "\"{name:开心}\"")
    @NotEmpty
    private String mood;

    @ApiModelProperty(value = "普通的数组。使用@RequestBody注解，会将对象全部转换成JSON。" +
            "如果请求参数不是JSON格式会报错HttpMessageNotReadableException:\n" +
            " JSON parse error: Can not deserialize instance of java.lang.Integer[] out of VALUE_STRING token;"
            , example = "[1,2]", dataType = "type:array")
    public Integer[] classIds;

}
