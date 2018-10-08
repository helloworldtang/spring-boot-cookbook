package com.tangcheng.learning.web.dto.resp;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.tangcheng.learning.web.dto.req.UserReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 10/2/2018 12:37 PM
 */
@Data
@EqualsAndHashCode(callSuper = true)
// TODO: 10/2/2018 冲突的问题
//@ApiModel("用户信息UserResp") 与 @JacksonXmlRootElement(localName = "UserDto")
@JacksonXmlRootElement(localName = "UserDto")
public class UserResp extends UserReq {
}
