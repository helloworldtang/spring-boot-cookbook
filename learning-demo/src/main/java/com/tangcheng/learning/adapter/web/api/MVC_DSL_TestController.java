package com.tangcheng.learning.adapter.web.api;

import com.tangcheng.learning.adapter.web.dto.req.SayHelloReq;
import com.tangcheng.learning.adapter.web.dto.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping("dsl")
public class MVC_DSL_TestController {

    @GetMapping
    @ApiOperation(value = "条件查询",notes = "条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", paramType = "query"),
    })
    public UserVO query(String username, String password) {
        log.info("多个参数用  @ApiImplicitParams");
        return new UserVO(1L, username, password);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取单条信息详情",notes = "获取单条信息详情")
    @ApiImplicitParam(name = "id", value = "用户编号", dataType = "long", paramType = "path")
    public ResponseEntity<UserVO> get(@PathVariable Long id) {
        log.info("单个参数用  @ApiImplicitParam");
        UserVO userVO = new UserVO(id, "swagger", "swagger");
        return ResponseEntity.ok(userVO);
    }

    @ApiOperation(value = "RequestBody 校验DSL", notes = "RequestBody 校验DSL")
    @PostMapping("/say/hello")
    public ResponseEntity<String> sayHello(@Valid @RequestBody SayHelloReq request) {
        log.info("如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam ，swagger 也会使用默认的参数名作为描述信息");

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
