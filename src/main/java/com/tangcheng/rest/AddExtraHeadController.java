package com.tangcheng.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MyWorld on 2016/9/25.
 */
@RestController
public class AddExtraHeadController {

    @RequestMapping(value = "/addHead/{name}",method = RequestMethod.GET)
    public TestRequest addExtraHeadInfo(TestRequest request) {
        return request;
    }

}

class TestRequest{
    private long pid;
    private long id;
    private String name;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
