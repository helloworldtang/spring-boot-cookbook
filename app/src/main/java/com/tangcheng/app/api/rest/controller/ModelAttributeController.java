package com.tangcheng.app.api.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tangcheng on 3/25/2017.
 */
@RestController
@RequestMapping("test/mvc")
public class ModelAttributeController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ModelAttributeController.class);

    /**
     * 在Spring MVC里，@ModelAttribute通常使用在Controller方法的参数注解中，用于解释model entity，
     * 但同时，也可以放在方法注解里。
     * <p>
     * 如果把@ModelAttribute放在方法的注解上时，代表的是：
     * 该Controller的所有方法在调用前，先执行此@ModelAttribute方法。
     */
    @ModelAttribute
    public void modelAttribute() {
        LOGGER.info("model attribute is doing!");
    }

    /**
     * 调用接口后的输出结果：
     * 2017-03-25 13:30:32.825 INFO  [http-nio-9999-exec-7][ModelAttributeController] model attribute is doing!
     * 2017-03-25 13:30:32.827 INFO  [http-nio-9999-exec-7][ModelAttributeController] doing business
     *
     * @return
     */
    @RequestMapping(value = "ma", method = RequestMethod.GET)
    public ResponseEntity<String> modelAttributeTest() {
        LOGGER.info("doing business");
        return ResponseEntity.ok("sucess");
    }

}
