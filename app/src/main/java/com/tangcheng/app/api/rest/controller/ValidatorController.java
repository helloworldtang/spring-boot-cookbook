package com.tangcheng.app.api.rest.controller;

import com.tangcheng.app.domain.query.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tang.cheng on 2016/11/8.
 */

/**
 * javax.validation.Valid
 * org.springframework.validation.annotation.Validated的区别与联系：
 * 相同点：添加上面任何一个注解后，这个对象在DispatcherServlet中都会被校验
 * 不同点：
 * （1）@Valid是JDK中支持的，@Validated是Spring支持的
 * （2）@Valid注解，则所有的校验注解都会被执行；@Validated注解支持有选择性的支持校验，即支持group分组的概念
 * <p>
 * <p>
 * spring boot 1.4默认使用 hibernate validator 5.2.4 Final实现校验功能。
 * 只要配置依赖spring-boot-starter-web即可
 * <p>
 * hibernate validator 5.2.4 Final是JSR 349 Bean Validation 1.1的具体实现。
 */
@Slf4j
@Api(tags = "validator learning", description = "Input information check demo")
@RestController
@RequestMapping("v1/mvc/valids")
public class ValidatorController {


    /**
     * 方式1：
     * org.springframework.validation.annotation.Validated
     * <p>
     * <p>
     * ModelAttribute 是Spring mvc的注解，这里Swagger可以解析这个注解，获得User的属性描述
     *
     * @param student
     * @return
     */
    @RequestMapping(value = "validated", method = RequestMethod.GET)
    public Student validatedScene(@Validated @ModelAttribute Student student) {
        return student;
    }

    /**
     * 不使用Annotation时，不会对Student上的约束生效
     *
     * @param student
     * @return
     */
    @GetMapping
    public Student noAnnotationScene(@ModelAttribute Student student) {
        return student;
    }

    /**
     * 问题一 ：Controller API中用对象来接收url参数，用什么注解
     * 这个问题在上文中已经解决，基本分为两步：
     * 第一步：在配置文件中使用.ignoredParameterTypes(XXX.class)
     * 第二步：在api上方使用@ApiImplicitParams和@ApiImplicitParam注解
     */
    @ApiOperation(value = "The second method: test spring boot with hibernate validator",
            notes = "use javax.validation.Valid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", defaultValue = "Tom", value = "用户名", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "age", defaultValue = "20", value = "年龄", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "classes", defaultValue = "Freshman Year", value = "班级", required = true, dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "valid", method = RequestMethod.GET)
    public ResponseEntity<Student> validScene(@Valid @ModelAttribute Student student) {
        return ResponseEntity.ok(student);
    }

    @ApiOperation(value = "添加一条记录", notes = "添加一条记录")
    @PostMapping
    public ResponseEntity<Student> createStudent(@Validated @RequestBody Student student) {
        long id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        log.info("id:{}", id);
        student.setId(id);
        return ResponseEntity.ok(student);
    }

}

