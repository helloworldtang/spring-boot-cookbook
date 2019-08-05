package com.tangcheng.learning.web.dto.bo;

import com.tangcheng.learning.web.dto.req.group.CreateGroup;
import com.tangcheng.learning.web.dto.req.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/12/07 11:01
 */
@ApiModel("BU数据")
@Data
public class BUBO {

    @ApiModelProperty(value = "BU编号", required = true, example = "1")
    @NotNull(groups = {CreateGroup.class})
    @Min(value = 1, groups = {CreateGroup.class, UpdateGroup.class})
    private Integer id;

    @ApiModelProperty(value = "BU名", required = true, example = "关卡名_1")
    @NotEmpty(groups = {CreateGroup.class})
    @Length(max = 10, groups = {CreateGroup.class, UpdateGroup.class})
    private String name;

}
