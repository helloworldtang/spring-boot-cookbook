package com.tangcheng.learning.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangcheng
 * 2018/03/06
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
