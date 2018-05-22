package com.tangcheng.learning.web.api;

import com.tangcheng.learning.web.dto.req.SayHelloRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @author tangcheng
 * 2018/05/02
 */
@Api(tags = "Case:MVC参数校验DSL ", description = "MVC参数校验DSL")
@Slf4j
@RestController
public class MVC_DSL_TestController {

    @ApiOperation(value = "RequestBody 校验DSL", notes = "RequestBody 校验DSL")
    @PostMapping("/say/hello")
    public ResponseEntity<String> sayHello(@Valid @RequestBody SayHelloRequest request) {
        Integer[] classIds = request.getClassIds();
        if (classIds == null) {
            throw new IllegalArgumentException("classIds is null");
        }
        return ResponseEntity.ok(request.getUserId() + request.getContent() + request.getMood());
    }


    @ApiOperation(value = "upload", notes = "如果添加@RequestBody注解，会报错：org.springframework.web.HttpMediaTypeNotSupportedException" +
            "Content type 'multipart/form-data;boundary=----WebKitFormBoundaryKz1zt5cbBPPXkMrt;charset=UTF-8' not supported")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        log.info("name:{}", multipartFile.getName());//@RequestParam("file") ,引自会返回 file
        return ResponseEntity.ok(multipartFile.getName() + "  " + multipartFile.getOriginalFilename() + " " + multipartFile.getContentType() + " " + multipartFile.getSize());
    }

    /**
     * attribute 'value' and its alias 'name' are present with values of [headerArg] and [Header中传的参数], but only one is permitted.
     *
     * @param headerArg
     * @return
     */
    @ApiOperation(value = "@RequestHeader", notes = "@RequestHeader")
    @GetMapping("header")
    public ResponseEntity<String> sayHello(@RequestHeader(value = "headerArg", defaultValue = "helloworld") String headerArg) {
        return ResponseEntity.ok("headerArg:" + headerArg);
    }

}
