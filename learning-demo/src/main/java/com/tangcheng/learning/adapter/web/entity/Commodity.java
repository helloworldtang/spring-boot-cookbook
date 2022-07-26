package com.tangcheng.learning.adapter.web.entity;

import com.tangcheng.learning.adapter.web.dto.req.CommodityCreateReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/12/04 17:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Commodity extends CommodityCreateReq {

    /**
     * 主键ID
     */
    private Integer id;

}
