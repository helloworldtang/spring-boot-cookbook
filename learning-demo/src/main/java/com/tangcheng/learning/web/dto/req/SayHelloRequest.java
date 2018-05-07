package com.tangcheng.learning.web.dto.req;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author tangcheng
 * 2018/05/02
 */
@Data
public class SayHelloRequest {

    @ApiModelProperty(required = true, value = "UserId", dataType = "int", example = "123")
    @NotNull
    @Min(value = 1, message = "最小值必须大于1")
    private Integer userId;

    @ApiModelProperty(required = true, value = "内容", example = "Best Wish!")
    @NotEmpty
    private String content;


    @ApiModelProperty(example = "[http://1.com,http://2.com]")
    @NotEmpty
    @JSONField(jsonDirect = true)
    private String mood;

}
