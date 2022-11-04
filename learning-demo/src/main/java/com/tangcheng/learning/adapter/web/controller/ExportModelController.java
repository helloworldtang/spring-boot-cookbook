package com.tangcheng.learning.adapter.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * spring-boot-cookbook
 *
 * @Auther: cheng.tang
 * @Date: 2022/11/4 9:17 PM
 * @Description:
 */
@Controller
@Slf4j
public class ExportModelController {

    @GetMapping("download.html")
    public String downloadHtml() {
        return "download";
    }

}
