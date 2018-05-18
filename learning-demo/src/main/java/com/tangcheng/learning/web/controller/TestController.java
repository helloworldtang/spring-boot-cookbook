package com.tangcheng.learning.web.controller;

import com.tangcheng.learning.web.dto.req.DateReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.SimpleDateFormat;

/**
 * @author tangcheng
 * 2018/03/12
 */
@Api(tags = "Validation", description = "MVC相关功能TestCase")
@RestController
@Slf4j
public class TestController {

    /**
     * Spring MVC会自动将long型的时间转换成java.util.Date
     *
     * @param dateReq 请求信息
     * @return
     */
    @ApiOperation("Spring MVC会自动将long型的时间转换成java.util.Date")
    @PostMapping("/test/book-time")
    public ResponseEntity<Object> postBookTime(@Valid @RequestBody DateReq dateReq) {
        String formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateReq.getBookTime());
        log.info("{},{}", formatDate, dateReq.getBookTime());
        return ResponseEntity.ok().body(formatDate);
    }

}
