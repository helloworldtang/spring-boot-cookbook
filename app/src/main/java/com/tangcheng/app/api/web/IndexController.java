package com.tangcheng.app.api.web;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.tangcheng.app.domain.vo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by MyWorld on 2016/9/25.
 */
@Controller
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private Producer captchaProducer;

    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public ModelAndView home(@ApiIgnore Principal principal) {
        LOGGER.info("isRememberMeAuthenticated:{}", isRememberMeAuthenticated());
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("title", principal.getName() + " login success!");
        modelAndView.addObject("loginUserName", principal.getName());
        modelAndView.addObject("date", new Date());

        Person person1 = new Person();
        person1.setName("person1");
        person1.setAge(ThreadLocalRandom.current().nextInt());

        Person person2 = new Person();
        person2.setName("person2");
        person2.setAge(ThreadLocalRandom.current().nextInt(1, 150));

        List<Person> peopleList = newArrayList(person1, person2);
        modelAndView.addObject("peopleList", peopleList);

        Person person = new Person();
        person.setName("SinglePeople");
        person.setAge(10);
        modelAndView.addObject("singlePerson", person);
        return modelAndView;
    }


    /**
     * 判断用户是否从Remember Me Cookie自动登录
     *
     * @return
     */
    private boolean isRememberMeAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }


    @RequestMapping("captcha.jpg")
    public String verification(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(captchaProducer.createImage(capText), "jpg", out);
            out.flush();
        }
        return null;
    }


}
