package com.tangcheng.app.domain.errorcode;

import com.tangcheng.app.domain.exception.BizError;

/**
 * Created by tangcheng on 3/25/2017.
 */
public enum GlobalCode implements BizError {
    SUCCESS(0, "success"),
    FAIL(-1, "fail"),
    NOT_EXIST(-2, "not exist");
    private final int code;
    private String msg;

    GlobalCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public BizError setMsg(String msg) {
        this.msg = msg;
        return this;
    }


}
