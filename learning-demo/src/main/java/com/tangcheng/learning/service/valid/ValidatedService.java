/**
 * @Auther: cheng.tang
 * @Date: 2019/6/12
 * @Description:
 */
package com.tangcheng.learning.service.valid;

import com.tangcheng.learning.web.dto.dto.ValidateResultDTO;
import com.tangcheng.learning.web.dto.req.UserReq;

import javax.validation.constraints.NotNull;

/**
 * @Auther: cheng.tang
 * @Date: 2019/6/12
 * @Description:
 */
public interface ValidatedService {
    ValidateResultDTO testValidateService(@NotNull(message = "userId不能为空") Integer userId, UserReq userReq);
}
