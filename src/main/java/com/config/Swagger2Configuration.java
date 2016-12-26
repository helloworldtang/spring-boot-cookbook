package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by tang.cheng on 2016/9/29.
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket createRestApi() {//可以定义多个Docket，类似于sql中的group by
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("cookbook")//分组，在html页面展示时有用
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")//base,最终调用接口后会和paths拼接在一起
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rest"))
                .paths(PathSelectors.any())// .paths(Predicates.or(PathSelectors.regex("/api/.*")))//过滤的接口,此片过滤掉/api/打头的接口
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("Spring boot cookbook")
                .termsOfServiceUrl("https://github.com/helloworldtang/SpringBootCookbook")
                .version("0.1")
                .contact(new Contact("Tang.Cheng", "http://chaojihao.net/", "helloworld.tang@qq.com"))
                .build();
    }
}
