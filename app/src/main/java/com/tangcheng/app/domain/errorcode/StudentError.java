package com.tangcheng.app.domain.errorcode;

/**
 * Created by tangcheng on 3/25/2017.
 */

import com.tangcheng.app.domain.exception.BizError;

/**
 * 错误码文件分散到不同的domain，有利于解耦
 * Student的错误码从20000--201000
 */
public enum StudentError implements BizError {
    NotExist(20000, "invalid id"),
    Duplication(20001, "Duplication");

    private final int code;
    private String msg;

    StudentError(int code, String msg) {
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
