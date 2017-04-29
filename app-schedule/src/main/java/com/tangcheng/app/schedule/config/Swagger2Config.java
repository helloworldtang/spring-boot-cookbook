package com.tangcheng.app.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by tangcheng on 4/29/2017.
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("schedule cookbook")
                .description("side project")
                .termsOfServiceUrl("https://github.com/helloworldtang/spring-boot-cookbook")//服务条款
                .version("1.0.1")//本次发布的版本
                .contact(new Contact("Tang.Cheng", "https://github.com/helloworldtang/spring-boot-cookbook", "helloworld.tang@qq.com"))
                .build();

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                .groupName("schedule")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tangcheng.app.schedule.web"))
                .build()
                .apiInfo(apiInfo);
    }

}
