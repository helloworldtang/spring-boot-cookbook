package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.custom.AuthorSettings;

@RestController
@SpringBootApplication
public class Application /*extends SpringBootServletInitializer*/ {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    @Autowired
    private AuthorSettings authorSettings; //1

    @RequestMapping("/")
    public String index() {
        String result = "author name is " + authorSettings.getName() + " and author age is " + authorSettings.getAge();
        LOGGER.info("Visit / :{}", result);
        return result;
    }

 /*   @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Ch623Application.class);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
