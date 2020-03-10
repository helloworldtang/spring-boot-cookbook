package com.tangcheng.learning.validation.method.service;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

/**
 * @author: cheng.tang
 * @date: 2020/3/10
 * @see
 * @since
 */
@Validated(Default.class)
public interface MethodValidationService {

    String process(@NotNull(message = "id不能为空") @Min(value = 10, message = "id不能小于10") Integer id, @NotNull(message = "name不能为空") String name);

}
