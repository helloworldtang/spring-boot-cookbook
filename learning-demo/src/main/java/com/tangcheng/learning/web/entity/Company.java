package com.tangcheng.learning.web.entity;

import com.tangcheng.learning.web.dto.req.CompanyReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/12/07 10:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Company extends CompanyReq {

    private Integer id;

}
