/**
 * @Auther: cheng.tang
 * @Date: 2019/6/12
 * @Description:
 */
package com.tangcheng.learning.service.valid.impl;

import com.tangcheng.learning.service.valid.ValidatedService;
import com.tangcheng.learning.web.dto.dto.ValidateResultDTO;
import com.tangcheng.learning.web.dto.req.UserReq;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @Auther: cheng.tang
 * @Date: 2019/6/12
 * @Description:
 */
@Service
@Validated
public class ValidatedServiceImpl implements ValidatedService {

    @Override
    public ValidateResultDTO testValidateService(@NotNull(message = "userId不能为空") Integer userId, UserReq userReq) {
        ValidateResultDTO dto = new ValidateResultDTO();
        dto.setAge(userId);
        dto.setName("N");
        return dto;
    }

}
