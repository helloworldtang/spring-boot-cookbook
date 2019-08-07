package com.tangcheng.app.domain.vo;


import com.alibaba.fastjson.annotation.JSONField;
import com.tangcheng.app.domain.errorcode.GlobalCode;
import com.tangcheng.app.domain.exception.BizError;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by tangcheng on 3/25/2017.
 */
@Builder
public class ResultData<T> implements BizError {

    @Builder.Default
    private BizError bizError = GlobalCode.SUCCESS;

    @Getter
    @JSONField(name = "result")
    private T detail;


    @Override
    public Integer getCode() {
        return bizError.getCode();
    }

    @Override
    public String getMsg() {
        return bizError.getMsg();
    }

    @Override
    public BizError setMsg(String msg) {
        bizError.setMsg(msg);
        return bizError;
    }


}
