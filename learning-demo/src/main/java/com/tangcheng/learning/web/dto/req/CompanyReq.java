package com.tangcheng.learning.web.dto.req;

import com.tangcheng.learning.web.dto.bo.BUBO;
import com.tangcheng.learning.web.dto.req.group.CreateGroup;
import com.tangcheng.learning.web.dto.req.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/12/07 10:50
 */
@Data
public class CompanyReq {

    @ApiModelProperty(value = "公司名", required = true, example = "公司名_1")
    @NotEmpty(groups = {CreateGroup.class})
    @Length(max = 10, groups = {CreateGroup.class, UpdateGroup.class})
    private String name;


    /**
     * 层级校验。
     * 即如果不加注解@Valid，则BUBO对象中的数据不会按写的注解进行校验
     */
    @Valid
    @ApiModelProperty(value = "BU的信息", required = true)
    @NotNull(groups = {CreateGroup.class})
    private BUBO bubo;

}
