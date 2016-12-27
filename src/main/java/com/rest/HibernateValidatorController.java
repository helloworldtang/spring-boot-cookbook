package com.rest;

import com.domain.Student;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by tang.cheng on 2016/11/8.
 */

/**
 * spring boot 1.4默认使用 hibernate validator 5.2.4 Final实现校验功能。
 * 只要配置依赖spring-boot-starter-web即可
 * <p>
 * hibernate validator 5.2.4 Final是JSR 349 Bean Validation 1.1的具体实现。
 */
@RestController
public class HibernateValidatorController {


    /**
     * 方式1：
     * org.springframework.validation.annotation.Validated
     *
     * @param student
     * @return
     */
    @RequestMapping(value = "Validated", method = RequestMethod.GET)
    public Student testValidatorWithValidated(@Validated Student student) {
        return student;
    }

    /**
     * 不使用Annotation时，不会对Student上的约束失效
     *
     * @param student
     * @return
     */
    @RequestMapping(value = "noValid", method = RequestMethod.GET)
    public Student testValidatorWithoutAnnotation(Student student) {
        return student;
    }

    /**
     * 问题一 ：Controller API中用对象来接收url参数，用什么注解
     * 这个问题在上文中已经解决，基本分为两步：
     * 第一步：在配置文件中使用.ignoredParameterTypes(XXX.class)
     * 第二步：在api上方使用@ApiImplicitParams和@ApiImplicitParam注解
     */
    @ApiOperation(value = "The second method: test spring boot with hibernate validator",
            notes = "use javax.validation.Valid",
            response = Student.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", defaultValue = "Tom", value = "用户名", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "age", defaultValue = "20", value = "年龄", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "classes", defaultValue = "G1", value = "班级", required = true, dataType = "string", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Student.class)
    })
    @RequestMapping(value = "Valid", method = RequestMethod.GET)
    public Student testValidatorWithValid(@Valid Student student) {
        return student;
    }


}

