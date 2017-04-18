package com.tangcheng.rest;

import com.tangcheng.demo.custom.AuthorSettings;
import com.tangcheng.demo.custom.ThreadSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by MyWorld on 2016/9/25.
 */
@Controller
public class DefaultController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private AuthorSettings authorSettings;

    @Autowired
    private ThreadSettings threadSettings;

    @RequestMapping(value = "/config/auto", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        String result = "author name is " + authorSettings.getName() +
                ", author age is " + authorSettings.getAge() +
                ",thread count:" + threadSettings.getCount();
        LOGGER.info("Visit / :{}", result);
        return result;
    }

    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public ModelAndView home() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("title", "Login success!");
        modelAndView.addObject("message", userDetails.getUsername());
        modelAndView.addObject("date", new Date());
        return modelAndView;
    }

}
