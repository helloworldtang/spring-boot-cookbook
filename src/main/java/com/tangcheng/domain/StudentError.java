package com.tangcheng.domain;

/**
 * Created by tangcheng on 3/25/2017.
 */

import com.tangcheng.global.exception.BizError;

/**
 * Student的错误码从20000--201000
 */
public enum StudentError implements BizError {
    NotExist(20000, "invalid id"),
    Duplication(20001, "Duplication");

    private final int code;
    private final String msg;

    StudentError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
