package com.rest;

import com.Application;
import com.custom.AuthorSettings;
import com.custom.ThreadSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MyWorld on 2016/9/25.
 */
@RestController
public class DefaultController {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private AuthorSettings authorSettings; //1

    @Autowired
    private ThreadSettings threadSettings;

    @RequestMapping("/")
    public String index() {
        String result = "author name is " + authorSettings.getName() +
                ", author age is " + authorSettings.getAge() +
                ",count:" + threadSettings.getCount();
        LOGGER.info("Visit / :{}", result);
        return result;
    }
}
