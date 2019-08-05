package com.tangcheng.app.rest.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsrfController {

    /**
     * http://docs.spring.io/spring-security/site/docs/4.2.x/reference/htmlsingle/#websocket-sameorigin-csrf
     *
     * @param token
     * @return
     */
    @RequestMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        /**
         * {"headerName":"X-CSRF-TOKEN","parameterName":"_csrf","token":"b7ce0199-206b-449c-b17a-66f665a94a38"}
         */
        return token;
    }
}