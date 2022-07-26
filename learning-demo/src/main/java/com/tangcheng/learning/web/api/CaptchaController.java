package com.tangcheng.learning.web.api;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * spring-boot-cookbook
 *
 * @Auther: cheng.tang
 * @Date: 2022/7/26 9:19 AM
 * @Description:
 */
@Slf4j
@Controller
public class CaptchaController {


    @RequestMapping("/demo/captcha.jpg")
    public void verification(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
//定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        String code = lineCaptcha.getCode();
        log.info(" {} ", code);
//图形验证码写出，可以写出到文件，也可以写出到流
        request.getSession().setAttribute("key", code);

        try (ServletOutputStream out = response.getOutputStream()) {
            lineCaptcha.write(out);
//            ImageIO.write(captchaProducer.createImage(capText), "jpg", out);
            out.flush();
        }
    }
}
