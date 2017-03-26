package com.tangcheng.db.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created by tang.cheng on 2016/12/27.
 */

@ApiModel
public class Student {
    //在需要校验的字段上指定约束条件
    @ApiModelProperty(example = "Tom", value = "TomValue")
    @NotBlank
    private String name;

    @ApiModelProperty(example = "10", value = "20")
    @Min(3)
    private int age;

    @ApiModelProperty(example = "Freshman Year", value = "Freshman Year value")
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
