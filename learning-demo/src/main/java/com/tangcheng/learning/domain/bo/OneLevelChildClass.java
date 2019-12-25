package com.tangcheng.learning.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: cheng.tang
 * @date: 2019/12/25
 * @see
 * @since
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OneLevelChildClass extends ParentClass {

    private String oneLevelChildName;

}
