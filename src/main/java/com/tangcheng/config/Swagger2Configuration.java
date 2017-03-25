package com.tangcheng.config;

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

    /**
     * ApiImplicitParam 与 ApiParam 的区别
     * ApiImplicitParam: This is the only way to define parameters when using Servlets or other non-JAX-RS environments.
     * <p>
     * 对Servlets或者非 JAX-RS的环境，只能使用 ApiImplicitParam。
     * 在使用上，ApiImplicitParam比ApiParam具有更少的代码侵入性，只要写在方法上就可以了，但是需要提供具体的属性才能配合swagger ui解析使用。
     * ApiParam只需要较少的属性，与swagger ui配合更好。
     * 传递复杂对象 By ModelAttribute
     * (1) ModelAttribute 是Spring mvc的注解，这里Swagger可以解析这个注解，获得User的属性描述--good
     * 原文链接：http://www.jianshu.com/p/b730b969b6a2
     * (2)传递复杂对象 By RequestBody
     * json格式传递对象使用RequestBody注解
     * (3) PathVariable是Spring 的注解，对于这种简单的参数，就可以不用写ApiParam来描述接口参数。
     * <p>
     * <p>
     * Every Docket bean is picked up by the swagger-mvc framework - allowing for multiple
     * swagger groups i.e. same code base multiple swagger resource listings.
     * http://springfox.github.io/springfox/docs/current/#introduction
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)//Docket, Springfox’s, primary api configuration mechanism is initialized for swagger specification 2.0
                .groupName("cookbook")//分组，在html页面展示时有用。启用group选项会更改api json的访问路径。可以在/swagger-resources中location字段中找到
                .select()//select() returns an instance of ApiSelectorBuilder to give fine grained control over the endpoints exposed via swagger.
                .apis(RequestHandlerSelectors.basePackage("com.rest"))
                .paths(PathSelectors.any())// .paths(Predicates.or(PathSelectors.regex("/api/.*")))//过滤的接口,此片过滤掉/api/打头的接口
                .build()//The selector requires to be built after configuring the api and path selectors. Out of the box we provide predicates for regex, ant, any, none
//                .ignoredParameterTypes(Student.class)//swagger-ui.html中如果有返回值是Student时，就会显示 Response Class (Status 200) OK <span class="strong">Student is not defined!</span>
                .genericModelSubstitutes(DeferredResult.class)//异步http请求
                .forCodeGeneration(true)//By default, types with generics will be labeled with '\u00ab'(<<), '\u00bb'(>>), and commas. This can be problematic with things like swagger-codegen. You can override this behavior by implementing your own GenericTypeNamingStrategy.
                .pathMapping("/")// 在这里可以设置请求的统一前缀；默认请求都是以 / 根路径开始，如果我们的应用不是部署在根路径，比如以/platform（应用名）部署，则可以通过一下方式设置请求的统一前缀。
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)//使用默认的响应信息true：默认响应信息将会回到全局的响应信息中；false:不加到全局的响应信息中
                ;
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
                .termsOfServiceUrl("https://github.com/helloworldtang/SpringBootCookbook")//服务条款
                .version("0.1")//本次发布的版本
                .contact(new Contact("Tang.Cheng", "https://github.com/helloworldtang/SpringBootCookbook", "helloworld.tang@qq.com"))
                .build();
    }
}
