package com.rest;

import com.CookBookApplication;
import com.demo.custom.AuthorSettings;
import com.demo.custom.ThreadSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MyWorld on 2016/9/25.
 */
@RestController
public class DefaultController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CookBookApplication.class);

    @Autowired
    private AuthorSettings authorSettings;

    @Autowired
    private ThreadSettings threadSettings;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        String result = "author name is " + authorSettings.getName() +
                ", author age is " + authorSettings.getAge() +
                ",thread count:" + threadSettings.getCount();
        LOGGER.info("Visit / :{}", result);
        return result;
    }
}
