package com.tangcheng.learning.web.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author tangcheng
 * 2018/03/12
 */
@Data
public class DateReq {
    @ApiModelProperty(example = "1520826609461")
    private Date bookTime;
}
