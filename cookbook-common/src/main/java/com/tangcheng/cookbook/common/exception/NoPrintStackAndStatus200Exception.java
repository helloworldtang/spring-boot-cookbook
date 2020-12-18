package com.tangcheng.cookbook.common.exception;

import com.tangcheng.cookbook.common.domain.dto.BizError;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2020/12/18 5:50 下午
 * @see
 * @since
 */
public class NoPrintStackAndStatus200Exception extends BizErrorException{
    public NoPrintStackAndStatus200Exception(BizError bizError) {
        super(bizError);
    }

    public NoPrintStackAndStatus200Exception(BizError bizError, Throwable cause) {
        super(bizError, cause);
    }
}
