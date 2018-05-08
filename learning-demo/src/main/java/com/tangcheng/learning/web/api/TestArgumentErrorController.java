package com.tangcheng.learning.web.api;

import com.tangcheng.learning.web.dto.req.SayHelloRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @author tangcheng
 * 2018/05/02
 */
@Api(tags = "Valid 参数缺失，参数错误", description = "Valid 参数缺失，参数错误")
@Slf4j
@RestController
public class TestArgumentErrorController {

    @ApiOperation(value = "say Hello", notes = "say Hello notes")
    @PostMapping("/say/hello")
    public ResponseEntity<String> sayHello(@Valid @RequestBody SayHelloRequest request) {
        return ResponseEntity.ok(request.getUserId() + request.getContent() + request.getMood());
    }


    @ApiOperation(value = "upload", notes = "如果添加@RequestBody注解，会报错：org.springframework.web.HttpMediaTypeNotSupportedException" +
            "Content type 'multipart/form-data;boundary=----WebKitFormBoundaryKz1zt5cbBPPXkMrt;charset=UTF-8' not supported")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        log.info("name:{}", multipartFile.getName());//@RequestParam("file") ,引自会返回 file
        return ResponseEntity.ok(multipartFile.getName() + "  " + multipartFile.getOriginalFilename() + " " + multipartFile.getContentType() + " " + multipartFile.getSize());
    }

}
