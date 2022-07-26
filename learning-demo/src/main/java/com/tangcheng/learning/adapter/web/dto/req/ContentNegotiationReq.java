package com.tangcheng.learning.adapter.web.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.MediaType;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 10/2/2018 2:09 PM
 */
@Data
public class ContentNegotiationReq {

    @ApiModelProperty(value = "由客户端请求指定MIME类型或MIME类型列表.这是Spring MVC的默认选择.完全依赖这些头信息存在一定风险,支持至少一种备选方案."
            , example = "true")
    private Boolean acceptHeader;

    @ApiModelProperty(value = "URL路径文件扩展名后缀,这是我们的配置中的鉴别(Discriminator)选项"
            , example = "false")
    private Boolean favorPathExtension;

    @ApiModelProperty(value = "请求参数.使用一个具体的查询参数,该参数的默认名为format,可通过parameterName属性进行自定义,可能的期望值就是注册的格式后缀(xml、html、json及csv等)"
            , example = "false")
    private Boolean favorParameter;

    @ApiModelProperty(value = "让后缀到媒体类型的映射依赖于Java Activation Framework，而非Spring MVC本身（例如json对应application/json,xml对应于application/xml，等等）"
            , example = "false")
    private Boolean useJaf;

    @ApiModelProperty(value = "最后，带有@RequestMapping注解（特别是带有produces属性）的控制器最终决定渲染的格式",
            example = MediaType.APPLICATION_XML_VALUE)
    private String producesProperty;

}
