package com.tangcheng.app.domain.exception;

/**
 * Created by tangcheng on 3/25/2017.
 */
public class BizException extends RuntimeException {
    private final BizError bizError;

    public BizException(BizError bizError) {
        this.bizError = bizError;
    }

    public BizException(BizError bizError, Throwable cause) {
        super(cause);
        this.bizError = bizError;
    }

    public BizError getBizError() {
        return bizError;
    }

    @Override
    public String getMessage() {
        return this.bizError.getMsg();
    }
}
