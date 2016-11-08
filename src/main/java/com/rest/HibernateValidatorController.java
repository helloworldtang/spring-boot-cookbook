package com.rest;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

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
    @RequestMapping(value = "Validated")
    public Student testValidatorWithValidated(@Validated Student student) {
        return student;
    }

    /**
     * 方式2：
     * javax.validation.Valid
     *
     * @param student
     * @return
     */
    @RequestMapping(value = "Valid")
    public Student testValidatorWithValid(@Valid Student student) {
        return student;
    }

    /**
     * 不使用Annotation时，不会对Student上的约束失效
     *
     * @param student
     * @return
     */
    @RequestMapping(value = "noValid")
    public Student testValidatorWithoutAnnotation(Student student) {
        return student;
    }


}

class Student {
    //在需要校验的字段上指定约束条件
    @NotBlank
    private String name;
    @Min(3)
    private int age;
    @NotBlank
    private String classes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

}
