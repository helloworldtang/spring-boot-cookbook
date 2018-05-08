package com.tangcheng.learning.web.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tangcheng
 * 2018/03/06
 */
@Api(hidden = true, tags = "WEB层", description = "WEB层")
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
