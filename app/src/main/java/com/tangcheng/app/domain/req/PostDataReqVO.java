package com.tangcheng.app.domain.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author tangcheng
 * 2017/12/18
 */
@Data
@NoArgsConstructor
@ApiModel("Post Data")
public class PostDataReqVO {

    @NotEmpty
    @ApiModelProperty(required = true, name = "数据", value = "上报服务器的数据")
    private String data;

    @NotEmpty
    @ApiModelProperty(required = true, name = "描述", value = "相关的描述信息")
    private String description;

}
