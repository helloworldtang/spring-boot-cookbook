package com.tangcheng.cookbook.common.domain.dto;

public interface BizError {
    Integer getCode();

    String getMsg();

    BizError setMsg(String msg);
}
