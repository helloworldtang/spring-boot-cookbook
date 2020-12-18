package com.tangcheng.cookbook.common.exception;

import com.tangcheng.cookbook.common.domain.dto.BizError;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2020/12/18 5:54 下午
 * @see
 * @since
 */
public class Status200Exception extends BizErrorException {
    public Status200Exception(BizError bizError) {
        super(bizError);
    }

    public Status200Exception(BizError bizError, Throwable cause) {
        super(bizError, cause);
    }
}
