package com.tangcheng.learning.adapter.web.dto.req;

import com.tangcheng.learning.adapter.web.dto.bo.CommodityColorBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/12/04 17:03
 */
@ApiModel("OrderCreateReq_订单信息")
@Data
public class CommodityCreateReq {

    @ApiModelProperty(value = "商品名称", required = true, example = "商品名称_1")
    @NotEmpty
    private String name;

    @ApiModelProperty(value = "商品价格", required = true, example = "9.00")
    @NotNull
    @DecimalMin("0")
    private BigDecimal prize;

    @ApiModelProperty(value = "商品可选颜色列表", required = true, example = "['#FFF','#F0F']")
    @NotEmpty
    private List<CommodityColorBO> colorList;

}

