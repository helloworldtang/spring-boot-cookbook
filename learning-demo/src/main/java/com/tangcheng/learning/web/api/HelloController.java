package com.tangcheng.learning.web.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    public static final String GREETINGS_FROM_SPRING_BOOT = "Greetings from Spring Boot!";

    @RequestMapping("/v1/hello")
    public String index() {
        return GREETINGS_FROM_SPRING_BOOT;
    }

}