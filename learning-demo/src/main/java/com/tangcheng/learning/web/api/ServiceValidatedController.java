/**
 * @Auther: cheng.tang
 * @Date: 2019/6/12
 * @Description:
 */
package com.tangcheng.learning.web.api;

import com.tangcheng.learning.service.valid.ValidatedService;
import com.tangcheng.learning.web.dto.dto.ValidateResultDTO;
import com.tangcheng.learning.web.dto.req.UserReq;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: cheng.tang
 * @Date: 2019/6/12
 * @Description:
 */
@Slf4j
@Api(tags = "SpringBoot Validation", description = "SpringBoot Validation")
@RestController
@RequestMapping
public class ServiceValidatedController {

    @Autowired
    private ValidatedService validatedService;

    @PostMapping("v1/validate")
    public ValidateResultDTO testValidateService(@RequestParam(required = false) Integer userId, @RequestBody UserReq userReq) {
        return validatedService.testValidateService(userId, userReq);
    }

}
