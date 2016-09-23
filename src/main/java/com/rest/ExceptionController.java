package com.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MyWorld on 2016/9/23.
 */
@RestController
public class ExceptionController {

    @RequestMapping("/exception")
    public String createException() {
        int j = 1;
        int i = 0;
        if (j / i > 0) {
            System.out.println(" no ");
        }
        return "Exception demo";
    }


    @RequestMapping("/throwable")
    public String throwable() throws Throwable {
        throw new Throwable("Throwable Demo!");
    }


}
