package com.tangcheng.app.rest.controller;

import com.tangcheng.app.domain.query.TestRequest;
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

