package com.tangcheng.learning.adapter.web.api;

import com.tangcheng.component.log.aop.annotation.LogReqBizParameters;
import com.tangcheng.learning.adapter.web.dto.req.ContentNegotiationReq;
import com.tangcheng.learning.adapter.web.dto.req.DateReq;
import com.tangcheng.learning.adapter.web.dto.req.UserReq;
import com.tangcheng.learning.adapter.web.dto.resp.UserResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.SimpleDateFormat;

/**
 * @author tangcheng
 * 2018/03/12
 */
@Api(tags = "Case:MVC相关", description = "HttpMessageConverterTest")
@RestController
@RequestMapping("hmc")
@Slf4j
public class HttpMessageConverterTestController {

    /**
     * Spring MVC会自动将long型的时间转换成java.util.Date
     * <p>
     * 测试MVC会自动将long型的时间转换成java.util.Date  com.tangcheng.learning.adapter.web.api.HttpMessageConverterTestController#postBookTime,dateReq:DateReq(bookTime=Mon Mar 12 11:50:09 CST 2018), Header THE_BIZ_FLAG:null, url : http://localhost:1111/hmc/test/book-time cost:1ms
     *
     * @param dateReq 请求信息
     * @return
     * @LogReqBizParameters
     */
    @LogReqBizParameters(bizKey = "测试MVC会自动将long型的时间转换成java.util.Date")
    @ApiOperation("Spring MVC会自动将long型的时间转换成java.util.Date")
    @PostMapping("/test/book-time")
    public ResponseEntity<Object> postBookTime(@Valid @RequestBody DateReq dateReq) {
        String formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateReq.getBookTime());
        log.info("{},{}", formatDate, dateReq.getBookTime());
        return ResponseEntity.ok().body(formatDate);
    }


    /**
     * 2023-07-24 19:02:38.999  INFO 63349 --- [nio-1111-exec-1] c.t.c.l.a.a.LogReqBizParametersAspect    :  使用value设置bizkey com.tangcheng.learning.adapter.web.api.HttpMessageConverterTestController#logUser,req:UserReq(name=hmc, age=20), Header THE_BIZ_FLAG:null, url : http://localhost:1111/hmc/log cost:6ms
     *
     * @param req
     * @return
     */
    @LogReqBizParameters("使用value设置bizkey")
    @PostMapping(value = "log")
    public UserResp logUser(@Valid @RequestBody UserReq req) {
        UserResp resp = new UserResp();
        BeanUtils.copyProperties(req, resp);
        resp.setName("emmm!!!" + req.getName());
        return resp;
    }

    /**
     * 配置内容协商之XML,使用Accept头信息来改变资源的表述
     * 基于jackson-dataformat-xml
     * <p>
     * curl -X POST "http://localhost:8080/hmc/user" -H "accept: application/xml" -H "Content-Type: application/xml" -d "<?xml version=\"1.0\" encoding=\"UTF-8\"?><UserReq>\t<age>30</age>\t<name>dduspace</name></UserReq>"
     *
     * @param req
     * @return
     */
    @PostMapping(value = "user",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public UserResp createWithJacksonDataformatXml(@Valid @RequestBody UserReq req) {
        UserResp resp = new UserResp();
        BeanUtils.copyProperties(req, resp);
        resp.setName("emmm!!!" + req.getName());
        return resp;
    }


    /**
     * 配置内容协商之XML,使用Accept头信息来改变资源的表述
     * 基于xstream
     * <p>
     * curl -X POST "http://localhost:8080/hmc/content/negotiation" -H "accept: application/xml" -H "Content-Type: application/xml" -d "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ContentNegotiationReq>\t<accept_header>true</accept_header>\t<favor_parameter>false</favor_parameter>\t<favor_path_extension>false</favor_path_extension>\t<produces_property>false</produces_property>\t<use_jaf>false</use_jaf></ContentNegotiationReq>"
     *
     * @param req
     * @return
     */
    @PostMapping(value = "content/negotiation",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public ContentNegotiationReq createWithXstream(@RequestBody ContentNegotiationReq req) {
        req.setProducesProperty(MediaType.ALL_VALUE);
        req.setFavorPathExtension(true);
        return req;
    }


}
