package com.tangcheng.learning.web.api;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/07/06 15:05
 */
@Api(tags = "请求参数初始化过程debug，【结论】没有使用@RequestBody修饰的入参，不能是Java接口", description = "【结论】没有使用@RequestBody修饰的入参，不能是接口")
@RestController
@Slf4j
public class RequestParameterInitTestController {

    @ApiOperation(value = "【失败】get方法，给入参数没有使用任何注解", notes = "【失败】Failed to instantiate [java.util.List]: Specified class is an interface")
    @GetMapping("list")
    public ResponseEntity<String> getList(List<String> request) {
        log.info("getList,request:{}", request);
        //        【失败】
//        org.springframework.beans.BeanInstantiationException: Failed to instantiate [java.util.List]: Specified class is an interface
//        at org.springframework.beans.BeanUtils.instantiateClass(BeanUtils.java:99) ~[spring-beans-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//        at org.springframework.web.method.annotation.ModelAttributeMethodProcessor.createAttribute(ModelAttributeMethodProcessor.java:139) ~[spring-web-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//        at org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor.createAttribute(ServletModelAttributeMethodProcessor.java:82) ~[spring-webmvc-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//        at org.springframework.web.method.annotation.ModelAttributeMethodProcessor.resolveArgument(ModelAttributeMethodProcessor.java:106) ~[spring-web-4.3.17.RELEASE.jar:4.3.17.RELEASE]
        return ResponseEntity.ok(JSON.toJSONString(request));
    }

    @ApiOperation(value = "【失败】get方法，给入参数使用@RequestBody注解", notes = "【失败】TypeError: Failed to execute 'fetch' on 'Window': Request with GET/HEAD method cannot have body.")
    @GetMapping("listWithRequestBody")
    public ResponseEntity<String> getListWithRequestBody(@RequestBody List<String> request) {
//      使用下面的命令可以调通，但使用swagger try it out时会报错：TypeError: Failed to execute 'fetch' on 'Window': Request with GET/HEAD method cannot have body.
//        PostMan中也会不支持传 json
//      curl -X GET "http://localhost:8080/listWithRequestBody" -H "accept: */*" -H "Content-Type: application/json" -d "[ \"123\"]"
        log.info("getListWithRequestBody,request:{}", request);
        return ResponseEntity.ok(JSON.toJSONString(request));
    }

    @ApiOperation(value = "【失败】post方法，给入参数没有使用任何注解", notes = "【失败】Failed to instantiate [java.util.List]: Specified class is an interface")
    @PostMapping("list")
    public ResponseEntity<String> postList(List<String> request) {
        log.info("postList,request:{}", request);
        //        【失败】
//        org.springframework.beans.BeanInstantiationException: Failed to instantiate [java.util.List]: Specified class is an interface
//        at org.springframework.beans.BeanUtils.instantiateClass(BeanUtils.java:99) ~[spring-beans-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//        at org.springframework.web.method.annotation.ModelAttributeMethodProcessor.createAttribute(ModelAttributeMethodProcessor.java:139) ~[spring-web-4.3.17.RELEASE.jar:4.3.17.RELEASE]
        return ResponseEntity.ok(JSON.toJSONString(request));
    }

    @ApiOperation(value = "【可以正常响应】post方法，给入参数使用@RequestBody注解", notes = "【SUCCESS】")
    @PostMapping("listWithRequestBody")
    public ResponseEntity<String> postListWithRequestBody(@RequestBody List<String> request) {
        log.info("postListWithRequestBody,request:{}", request);
//        【可以正常响应】
        return ResponseEntity.ok(JSON.toJSONString(request));
    }


}
