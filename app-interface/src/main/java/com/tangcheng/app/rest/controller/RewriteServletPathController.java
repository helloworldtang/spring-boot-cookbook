package com.tangcheng.app.rest.controller;

import com.tangcheng.app.domain.query.PathParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tang.cheng on 2016/9/27.
 */
@RestController
public class RewriteServletPathController {
    public static final String CHANGE_USER_URL = "/change/user";

    @ApiOperation(value = "获取请求信息", notes = "通过重写HttpServletRequest来更改入参")
    @ApiImplicitParams({
            //此处只有一个参数，单独使用ApiImplicitParam也可以
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long")
    })
    @RequestMapping(value = CHANGE_USER_URL + "/{userId}", method = RequestMethod.GET)
    public PathParam pathPathVariable(HttpServletRequest request, @PathVariable("userId") String userId) {
        return new PathParam("PathVariable", request.getRequestURI(), request.getQueryString(), userId);
    }

    /**
     * 经过重写HttpServletRequestWrapper中的getServletPath方法，这个rest接口永远不会被调用到
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = CHANGE_USER_URL, method = RequestMethod.GET)
    public String pathRequestParam(@RequestParam("userId") String userId) {
        return new PathParam("RequestParam", "PathVariable", CHANGE_USER_URL, userId).toString();
    }


}


