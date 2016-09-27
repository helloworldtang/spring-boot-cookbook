package com.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tang.cheng on 2016/9/27.
 */
@RestController
public class RewriteServletPathController {
    public static final String CHANGE_USER_URL = "/change/user";

    @RequestMapping(CHANGE_USER_URL + "/{userId}")
    public PathParam pathPathVariable(@PathVariable("userId") String userId) {
        return new PathParam("PathVariable", CHANGE_USER_URL, userId);
    }

    /**
     * 经过重写HttpServletRequestWrapper中的getServletPath方法，这个rest接口永远不会被调用到
     *
     * @param userId
     * @return
     */
    @RequestMapping(CHANGE_USER_URL)
    public String pathRequestParam(@RequestParam("userId") String userId) {
        return new PathParam("RequestParam", CHANGE_USER_URL, userId).toString();
    }


}

class PathParam {

    private String type;
    private String changeUserUrl;
    private String userId;

    public PathParam(String type, String changeUserUrl, String userId) {
        this.type = type;
        this.changeUserUrl = changeUserUrl;
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChangeUserUrl() {
        return changeUserUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setChangeUserUrl(String changeUserUrl) {
        this.changeUserUrl = changeUserUrl;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PathParam{" +
                "type='" + type + '\'' +
                ", changeUserUrl='" + changeUserUrl + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
