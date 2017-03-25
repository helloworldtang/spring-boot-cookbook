package com.tangcheng.rest;

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
    @RequestMapping(value = CHANGE_USER_URL + "/{userId}",method = RequestMethod.GET)
    public PathParam pathPathVariable(HttpServletRequest request, @PathVariable("userId") String userId) {
        return new PathParam("PathVariable", request.getRequestURI(), request.getQueryString(), userId);
    }

    /**
     * 经过重写HttpServletRequestWrapper中的getServletPath方法，这个rest接口永远不会被调用到
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = CHANGE_USER_URL,method = RequestMethod.GET)
    public String pathRequestParam(@RequestParam("userId") String userId) {
        return new PathParam("RequestParam", "PathVariable", CHANGE_USER_URL, userId).toString();
    }


}

class PathParam {

    private String type;
    private String uri;
    private String queryString;
    private String userId;

    public PathParam(String type, String uri, String queryString, String userId) {
        this.type = type;
        this.uri = uri;
        this.queryString = queryString;
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getUserId() {
        return userId;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "PathParam{" +
                "type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                ", queryString='" + queryString + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
