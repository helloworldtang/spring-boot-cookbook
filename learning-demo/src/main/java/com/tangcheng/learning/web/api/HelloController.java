package com.tangcheng.learning.web.api;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "UT,IT示例类", description = "UT,IT示例类")
@Slf4j
@RestController
public class HelloController {

    public static final String GREETINGS_FROM_SPRING_BOOT = "Greetings from Spring Boot!";

    @GetMapping("/v1/hello")
    public String index(HttpServletRequest httpServletRequest) {
        log.info("ip:{} 客户机的端口:{} 服务端口:{}  请求最终在哪个端口结束:{}", httpServletRequest.getRemoteAddr(),
                httpServletRequest.getRemotePort(),
                httpServletRequest.getServerPort(),
                httpServletRequest.getLocalPort()
        );
        return GREETINGS_FROM_SPRING_BOOT;
    }

}