package com.tangcheng.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * Created by tang.cheng on 2017/4/22.
 */
@EnableOpenApi
@SpringBootApplication
public class LearningDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningDemoApplication.class, args);
    }

}
