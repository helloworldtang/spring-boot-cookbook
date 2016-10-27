package com.rest;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tang.cheng on 2016/10/19.
 */
@RestController
public class RestTemplateDemoController {

    public static final String AUTH = "auth";

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public Result getOnlyPathVariable(@PathVariable("userId") String userId) {
        return new Result(userId);
    }

    @RequestMapping(value = "/user/{userId}/detail", method = RequestMethod.GET)
    public Result getPathVariableAndRequestParameter(@PathVariable("userId") String userId,
                                                     @RequestParam(value = "pageId", required = false) Integer pageId,
                                                     @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return new Result(userId, pageId, pageSize);
    }

    @RequestMapping(value = "/user/{userId}/auth", method = RequestMethod.GET)
    public Result getPathVariableAndRequestParameterAndHeader(HttpServletRequest request,
                                                              @PathVariable("userId") String userId,
                                                              @RequestParam(value = "pageId", required = false) Integer pageId,
                                                              @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        String auth = request.getHeader(AUTH);
        return new Result(userId, pageId, pageSize, auth);
    }


    @RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
    public Result postOnlyPathVariable(@PathVariable("userId") String userId) {
        return new Result(userId);
    }


    @RequestMapping(value = "/user/{userId}/detail", method = RequestMethod.POST)
    public Result postPathVariableAndRequestParameter(@PathVariable("userId") String userId,
                                                      @RequestParam(value = "pageId", required = false) Integer pageId,
                                                      @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return new Result(userId, pageId, pageSize);
    }


    @RequestMapping(value = "/user/{userId}/auth", method = RequestMethod.POST)
    public Result postPathVariableAndRequestParameterAndHeader(HttpServletRequest request,
                                                               @PathVariable("userId") String userId,
                                                               @RequestParam(value = "pageId", required = false) Integer pageId,
                                                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        String auth = request.getHeader(AUTH);
        return new Result(userId, pageId, pageSize, auth);
    }


}

class Result {

    private String userId;
    private Integer pageId;
    private Integer pageSize;
    private String auth;

    public Result() {
    }

    public Result(String userId, int pageId, int pageSize, String auth) {

        this.userId = userId;
        this.pageId = pageId;
        this.pageSize = pageSize;
        this.auth = auth;
    }

    public Result(String userId, int pageId, int pageSize) {

        this.userId = userId;
        this.pageId = pageId;
        this.pageSize = pageSize;
    }

    public Result(String userId) {

        this.userId = userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (userId != null ? !userId.equals(result.userId) : result.userId != null) return false;
        if (pageId != null ? !pageId.equals(result.pageId) : result.pageId != null) return false;
        if (pageSize != null ? !pageSize.equals(result.pageSize) : result.pageSize != null) return false;
        return auth != null ? auth.equals(result.auth) : result.auth == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (pageId != null ? pageId.hashCode() : 0);
        result = 31 * result + (pageSize != null ? pageSize.hashCode() : 0);
        result = 31 * result + (auth != null ? auth.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "userId='" + userId + '\'' +
                ", pageId=" + pageId +
                ", pageSize=" + pageSize +
                ", auth='" + auth + '\'' +
                '}';
    }
}
