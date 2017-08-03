package com.tangcheng.app.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * http://netkiller.github.io/java/spring/boot/session.html
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-08-03  12:30
 */
@RestController
public class HttpSessionController {

    @RequestMapping("/session/set")
    public String set(HttpSession session) {
        String key = "test";
        session.setAttribute(key, new Date());
        return key;
    }

    @RequestMapping("/session/get")
    public Map<String, Object> get(HttpSession session) {
        /**
         *
         {
         "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN": {
         "headerName": "X-CSRF-TOKEN",
         "parameterName": "_csrf",
         "token": "b409d267-2f7f-4b48-ab7c-4aa50dfcdfe3"
         },
         "KAPTCHA_SESSION_KEY": "2a3y",
         "SPRING_SECURITY_CONTEXT": {
         "authentication": {
         "authenticated": true,
         "authorities": [
         {
         "authority": "ROLE_ADMIN"
         }
         ],
         "credentials": "",
         "details": {
         "remoteAddress": "0:0:0:0:0:0:0:1",
         "sessionId": "5b0f7167-a187-4323-8642-74f76542a5c7"
         },
         "name": "admin",
         "principal": {
         "accountNonExpired": true,
         "accountNonLocked": true,
         "authorities": [
         {
         "$ref": "$.SPRING_SECURITY_CONTEXT.authentication.authorities[0]"
         }
         ],
         "credentialsNonExpired": true,
         "enabled": true,
         "password": "",
         "username": "admin"
         }
         }
         }
         }
         */

        Map<String, Object> sessionMap = new HashMap<>();
        // 获取session中所有的键值
        Enumeration<String> enumeration = session.getAttributeNames();
        // 遍历enumeration中的
        while (enumeration.hasMoreElements()) {
            // 获取session键值
            String name = enumeration.nextElement();
            sessionMap.put(name, session.getAttribute(name));
        }
        return sessionMap;
    }


}
