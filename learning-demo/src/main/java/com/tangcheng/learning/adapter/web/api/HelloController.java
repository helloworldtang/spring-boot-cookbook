package com.tangcheng.learning.adapter.web.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "UT,IT示例类", description = "UT,IT示例类")
@Slf4j
@RestController
public class HelloController {

    public static final String GREETINGS_FROM_SPRING_BOOT = "Greetings from Spring Boot!";

    @ApiOperation("使用一个接口同时具体Get和Post行为【提供给第三方回调时可能有用】")
    @RequestMapping(value = "/v1/hello", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest request) {
        log.info("ip:{} 客户机的端口:{} 服务端口:{}  请求最终在哪个端口结束:{}", request.getRemoteAddr(),
                request.getRemotePort(),
                request.getServerPort(),
                request.getLocalPort()
        );
        StringBuffer requestURL = request.getRequestURL();
        String requestURI = request.getRequestURI();
        String domainUrl = requestURL.delete(requestURL.length() - requestURI.length(), requestURL.length()).toString();
        log.info("domainUrl:{} , requestURL:{} , requestURI:{}", domainUrl, requestURL.toString(), requestURI);
        return GREETINGS_FROM_SPRING_BOOT;
    }


}