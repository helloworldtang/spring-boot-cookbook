/**
 * @Auther: cheng.tang
 * @Date: 2019/2/22
 * @Description:
 */
package com.tangcheng.learning.adapter.web.api;

import com.tangcheng.learning.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cheng.tang
 * @Date: 2019/2/22
 * @Description:
 */
@Slf4j
@Api(tags = "MVC注解@ModelAttribute和@InitBinder", description = "MVC注解@ModelAttribute和@InitBinder")
@RequestMapping("/v1/mvc/annotation")
@RestController
public class MvcAnnotationDemoController {

    @ApiOperation("通过@ModelAttribute注解注入值")
    @GetMapping("model-attribute/user")
    public ResponseEntity<User> modelAttributeUser(User user) {
        log.info("user:{}", user);
        return ResponseEntity.ok(user);
    }

}
