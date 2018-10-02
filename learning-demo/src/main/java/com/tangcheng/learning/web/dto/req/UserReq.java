package com.tangcheng.learning.web.dto.req;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 10/2/2018 12:31 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// TODO: 10/2/2018 // TODO: 10/2/2018 冲突的问题
//@ApiModel("用户信息UserReq") 与 @JacksonXmlRootElement(localName = "UserReq")冲突
@JacksonXmlRootElement(localName = "UserReq")
public class UserReq {

    @JacksonXmlProperty(localName = "name")
    @ApiModelProperty(value = "姓名", required = true, example = "dduspace")
    @NotEmpty
    @Length(max = 100, min = 2)
    private String name;

    @JacksonXmlProperty(localName = "age")
    @ApiModelProperty(value = "年龄", required = true, example = "30")
    @NotNull
    @Max(500)
    @Min(1)
    private Integer age;

}
