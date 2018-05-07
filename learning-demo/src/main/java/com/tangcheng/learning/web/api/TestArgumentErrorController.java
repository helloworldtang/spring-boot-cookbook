package com.tangcheng.learning.web.api;

import com.tangcheng.learning.web.dto.req.SayHelloRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author tangcheng
 * 2018/05/02
 */
@Api(tags = "Valid 参数缺失，参数错误", description = "Valid 参数缺失，参数错误")
@RestController
public class TestArgumentErrorController {

    @ApiOperation(value = "say Hello", notes = "say Hello notes")
    @PostMapping("/say/hello")
    public ResponseEntity<String> sayHello(@Valid @RequestBody SayHelloRequest request) {
        return ResponseEntity.ok(request.getUserId() + request.getContent() + request.getMood());
    }

}
