package com.tangcheng.learning.adapter.web.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tangcheng
 * 2018/03/06
 */
@Api(hidden = true, tags = "WEB层", description = "WEB层")
@Controller
public class IndexController {

    @GetMapping({"/", "home", "index", ""})
    public String index(Model model) {
        model.addAttribute("eventName", "FIFA 2018");
        return "index";
    }



}
