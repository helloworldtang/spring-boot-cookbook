package com.tangcheng.learning.web.dto.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@ApiModel("CommodityColor_商品颜色")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityColorBO {

    @ApiModelProperty(value = "商品颜色", required = true, example = "#FFF")
    @NotEmpty
    @Length(max = 50)
    private String color;

}
