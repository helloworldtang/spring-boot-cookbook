package com.config;

import com.domain.Student;
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
                .groupName("cookbook")//分组，在html页面展示时有用。启用group选项会更改api json的访问路径。可以在/swagger-resources中location字段中找到
                .ignoredParameterTypes(Student.class)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)//使用默认的响应信息true：默认响应信息将会回到全局的响应信息中；false:不加到全局的响应信息中
                .forCodeGeneration(true)//自动生成代码
                .pathMapping("/")//base,最终调用接口后会和paths拼接在一起
                .select()//启用api选择构建者(将创建一个新的构建者)
                .apis(RequestHandlerSelectors.basePackage("com.rest"))
                .paths(PathSelectors.any())// .paths(Predicates.or(PathSelectors.regex("/api/.*")))//过滤的接口,此片过滤掉/api/打头的接口
                .build();
    }

    /**
     * api描述信息
     *
     * @return
     */
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
