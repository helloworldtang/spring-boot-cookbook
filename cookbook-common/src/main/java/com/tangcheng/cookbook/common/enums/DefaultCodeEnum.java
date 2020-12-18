package com.tangcheng.cookbook.common.enums;

import com.tangcheng.cookbook.common.domain.dto.BizError;
import lombok.Getter;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2020/12/16 4:28 下午
 * @see
 * @since
 */
@Getter
public enum DefaultCodeEnum implements BizError {
    SUCCESS(200, ""),
    FAIL(500, "出现未知异常"),
    BAD_REQUEST(400, "参数错误"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_EXIST(404, "资源不存在"),
    DUPLICATION(600, "数据已存在"),
    INVALID_DATA(601, "数据无效"),
    REQUIRED_DATA(602, "客户物料单位存在时，客户标准单位数量和震坤行标准单位数量为必填"),
    NEED_DATA(603, "数据不完整"),
    NEED_SERVICE_AREA_DATA(604, "未获取到服务区域数据"),
    NEED_REGHT_NAME(605, "请输入正确的账号"),
    NOT_INNER_NAME(606, "非内部用户暂不支持登陆");

    private final int code;
    private String msg;

    DefaultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public BizError setMsg(String msg) {
        this.msg = msg;
        return this;
    }

}
