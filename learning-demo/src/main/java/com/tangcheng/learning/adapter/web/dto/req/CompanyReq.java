package com.tangcheng.learning.adapter.web.dto.req;

import com.tangcheng.learning.adapter.web.dto.bo.BUBO;
import com.tangcheng.learning.adapter.web.dto.req.group.CreateGroup;
import com.tangcheng.learning.adapter.web.dto.req.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/12/07 10:50
 */
@Data
public class CompanyReq {

    @ApiModelProperty(value = "公司名", required = true, example = "公司名_1")
    @NotEmpty(groups = {CreateGroup.class})
    @Length(max = 10, groups = {CreateGroup.class, UpdateGroup.class})
    private String name;

    /**
     * 层级校验
     * 【Spring mvc不支持对List<Bean>中的对象进行校验。因为List不是一个JavaBean。如果想要进行校验，必须Wrap your list inside a Java Bean，譬如此处的CompanyReq】
     * 即如果不加注解@Valid，则buBOs对象中的数据不会按写的注解进行校验
     * <p>
     * Spring文档中对validation的解读:
     * 3. Validation, Data Binding, and Type Conversion
     * https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
     * <p>
     * JavaBeans Components【Java Bean的定义】
     * JavaBeans components are Java classes that can be easily reused and composed together into applications. Any Java class that follows certain design conventions is a JavaBeans component.
     * <p>
     * JavaServer Pages technology directly supports using JavaBeans components with standard JSP language elements. You can easily create and initialize beans and get and set the values of their properties.
     * <p>
     * JavaBeans Component Design Conventions
     * JavaBeans component design conventions govern the properties of the class and govern the public methods that give access to the properties.
     * A JavaBeans component property can be:
     * Read/write, read-only, or write-only
     * Simple, which means it contains a single value, or indexed, which means it represents an array of values
     * A property does not have to be implemented by an instance variable. It must simply be accessible using public methods that conform to the following conventions:
     * For each readable property, the bean must have a method of the form:
     * PropertyClass getProperty() { ... }
     * For each writable property, the bean must have a method of the form:
     * setProperty(PropertyClass pc) { ... }
     * In addition to the property methods, a JavaBeans component must define a constructor that takes no parameters.
     * <p>
     * https://docs.oracle.com/javaee/5/tutorial/doc/bnair.html
     */
    @Valid
    @ApiModelProperty(value = "BU的信息", required = true)
    @NotEmpty(groups = {CreateGroup.class})
    private List<BUBO> buBOs;


}
