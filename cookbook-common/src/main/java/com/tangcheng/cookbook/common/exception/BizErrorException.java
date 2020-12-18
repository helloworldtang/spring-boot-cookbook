package com.tangcheng.cookbook.common.exception;

import com.tangcheng.cookbook.common.domain.dto.BizError;
import lombok.Getter;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2020/12/16 4:30 下午
 * @see
 * @since
 */
@Getter
public class BizErrorException extends RuntimeException {

    private BizError bizError;

    public BizErrorException(BizError bizError) {
        this.bizError = bizError;
    }

    public BizErrorException(BizError bizError, Throwable cause) {
        super(cause);
        this.bizError = bizError;
    }

    @Override
    public String getMessage() {
        return this.bizError.getMsg();
    }

}
