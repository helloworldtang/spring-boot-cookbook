package com.tangcheng.global.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.tangcheng.global.exception.BizError;
import com.tangcheng.global.exception.GlobalCode;

import java.io.Serializable;

/**
 * Created by tangcheng on 3/25/2017.
 */
public class ResultData<T> implements Serializable {
    private Integer code;
    private String msg;
    @JSONField(name = "result")
    private T detail;

    public ResultData(BizError bizError) {
        this.code = bizError.getCode();
        this.msg = bizError.getMsg();
    }

    public ResultData(BizError bizError, T detail) {
        this(bizError);
        this.detail = detail;
    }

    public ResultData(T detail) {
        this(GlobalCode.SUCCESS);
        this.detail = detail;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }

}
