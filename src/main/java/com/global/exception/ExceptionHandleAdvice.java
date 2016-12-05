package com.global.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MyWorld on 2016/9/23.
 */
@ControllerAdvice
public class ExceptionHandleAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandleAdvice.class);

    //Throwable.class的子类也可以的
    @ExceptionHandler(Throwable.class)
    public
    @ResponseBody
    RestError throwable(Throwable e) {
        LOGGER.error("{}", e.getClass(), e);
        RestError restError = new RestError();
        restError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        restError.setMessage(e.getMessage());
        restError.setDeveloperMessage("测试能不能拦截 Throwable.class");
        restError.setThrowable(e);
        return restError;
    }


    //Throwable.class的子类也可以的
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestError handleAllException(Exception e) {
        LOGGER.error("{}", e.getClass(), e);
        RestError restError = new RestError();
        restError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        restError.setMessage(e.getMessage());
        restError.setDeveloperMessage("拦截Exception就可以了，如果出现error，jvm已经挂了");
        restError.setThrowable(e);
        return restError;
    }

}
