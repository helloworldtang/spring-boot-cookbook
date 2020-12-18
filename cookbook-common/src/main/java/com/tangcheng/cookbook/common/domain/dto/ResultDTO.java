package com.tangcheng.cookbook.common.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tangcheng.cookbook.common.enums.DefaultCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class ResultDTO<T> implements BizError {

    @JSONField(serialize = false, deserialize = false)
    @JsonIgnore
    private transient BizError bizError;

    /**
     * 错误码
     */
    @Setter
    private Integer code;

    /**
     * 错误消息
     */
    private String msg;

    /**
     * 表示返回的结果集
     */
    @Getter
    @Setter
    private T data;

    /**
     * 代表接口处理响应结果成功还是失败
     * true:成功；false:失败
     */
    @Getter
    private Boolean success;

    public ResultDTO() {
        this.setBizError(DefaultCodeEnum.SUCCESS);
    }

    /**
     * 成功的请求
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> ok(T data) {
        ResultDTO<T> resultDTO = new ResultDTO<T>();
        resultDTO.setData(data);
        resultDTO.setBizError(DefaultCodeEnum.SUCCESS);
        return resultDTO;
    }

    /**
     * 成功的请求
     *
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> ok() {
        ResultDTO<T> resultDTO = new ResultDTO<T>();
        resultDTO.setBizError(DefaultCodeEnum.SUCCESS);
        return resultDTO;
    }

    /**
     * 异常的请求
     *
     * @return
     */
    public static ResultDTO<String> fail() {
        ResultDTO<String> resultDTO = new ResultDTO<>();
        resultDTO.setBizError(DefaultCodeEnum.FAIL.setMsg("未知异常"));
        return resultDTO;
    }

    /**
     * 异常的请求
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> fail(String msg) {
        return ResultDTO.fail(DefaultCodeEnum.FAIL.setMsg(msg));
    }

    /**
     * 异常的请求
     *
     * @param bizError
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> fail(BizError bizError) {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setBizError(bizError);
        return resultDTO;
    }

    /**
     * 异常的请求，有返回数据
     *
     * @param bizError
     * @param data
     * @return
     */
    public static <T> ResultDTO<T> fail(BizError bizError, T data) {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setBizError(bizError);
        resultDTO.setData(data);
        return resultDTO;
    }

    public void setBizError(BizError bizError) {
        this.bizError = bizError;
        if (bizError != null) {
            this.setCode(bizError.getCode());
            this.msg = bizError.getMsg();
            this.success = Objects.equals(this.bizError, DefaultCodeEnum.SUCCESS);
        }
    }

    /**
     * 500:失败；200:成功
     *
     * @return
     */
    @Override
    public Integer getCode() {
        return this.code;
    }

    /**
     * 接口返回数据的描述信息
     *
     * @return
     */
    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public BizError setMsg(String msg) {
        this.msg = msg;
        return bizError;
    }

}
