package com.tangcheng.app.rest.controller;

import com.tangcheng.app.domain.query.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tang.cheng on 2016/10/19.
 */
@Api(tags = "mock",description = "mock request")
@RestController
@RequestMapping("test")
public class MockController {

    public static final String AUTH = "auth";

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public Result getOnlyPathVariable(@PathVariable("userId") String userId) {
        return new Result(userId);
    }

    @RequestMapping(value = "{userId}/detail", method = RequestMethod.GET)
    public Result getPathVariableAndRequestParameter(@PathVariable("userId") String userId,
                                                     @RequestParam(value = "pageId", required = false) Integer pageId,
                                                     @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return new Result(userId, pageId, pageSize);
    }

    @RequestMapping(value = "{userId}/auth", method = RequestMethod.GET)
    public Result getPathVariableAndRequestParameterAndHeader(HttpServletRequest request,
                                                              @PathVariable("userId") String userId,
                                                              @RequestParam(value = "pageId", required = false) Integer pageId,
                                                              @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        String auth = request.getHeader(AUTH);
        return new Result(userId, pageId, pageSize, auth);
    }


    @RequestMapping(value = "{userId}", method = RequestMethod.POST)
    public Result postOnlyPathVariable(@PathVariable("userId") String userId) {
        return new Result(userId);
    }


    @RequestMapping(value = "{userId}/detail", method = RequestMethod.POST)
    public Result postPathVariableAndRequestParameter(@PathVariable("userId") String userId,
                                                      @RequestParam(value = "pageId", required = false) Integer pageId,
                                                      @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return new Result(userId, pageId, pageSize);
    }


    @RequestMapping(value = "{userId}/auth", method = RequestMethod.POST)
    public Result postPathVariableAndRequestParameterAndHeader(HttpServletRequest request,
                                                               @PathVariable("userId") String userId,
                                                               @RequestParam(value = "pageId", required = false) Integer pageId,
                                                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        String auth = request.getHeader(AUTH);
        return new Result(userId, pageId, pageSize, auth);
    }


}


