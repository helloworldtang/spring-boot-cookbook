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
    public String index(HttpServletRequest request) {
        log.info("ip:{} 客户机的端口:{} 服务端口:{}  请求最终在哪个端口结束:{}", request.getRemoteAddr(),
                request.getRemotePort(),
                request.getServerPort(),
                request.getLocalPort()
        );
        StringBuffer requestURL = request.getRequestURL();
        String domainUrl = requestURL.delete(requestURL.length() - request.getRequestURI().length(), requestURL.length()).toString();
        log.info("domainUrl : {}", domainUrl);
        return GREETINGS_FROM_SPRING_BOOT;
    }


}