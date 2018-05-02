package com.tangcheng.learning.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author tangcheng
 * 2018/05/02
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * javax.validation.Valid对Request参数时行校验，不合法会报org.springframework.validation.BindException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity handleBindException(BindException e) {
        log.error("BindException.class.msg:{}", e.getMessage(), e);
        StringBuilder result = new StringBuilder();
        for (FieldError fieldError : e.getFieldErrors()) {
            result.append(fieldError.getField()).append(":").
                    append(fieldError.getDefaultMessage()).
                    append(System.lineSeparator());
        }
        return ResponseEntity.badRequest().body(result.toString());
    }

    // TODO: 2018/5/2 这种异常是哪种情况 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException.class.msg:{}", e.getMessage(), e);
        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            builder.append(fieldError.getField())
                    .append(":")
                    .append(fieldError.getDefaultMessage())
                    .append(System.lineSeparator());
        }
        return ResponseEntity.badRequest().body(builder.toString());
    }


}
