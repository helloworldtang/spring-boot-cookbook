package com.tangcheng.learning.web.config;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

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


    /**
     * org.springframework.web.bind.ServletRequestBindingException: Missing request header 'userId' for method parameter of type Integer
     *使用@RequestHeader 且字段为必填时，报此错误
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServletRequestBindingException.class)
    @ResponseBody
    public Object exception(HttpServletRequest request, ServletRequestBindingException e) {
        log.error("ServletRequestBindingException.class.msg:{}", e);

        String[] split = e.getMessage().split("'");
        if (split.length > 2) {
            return ResponseEntity.badRequest().body(String.join(",", split[0], split[1]));
        }
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    /**
     * .w.s.m.s.DefaultHandlerExceptionResolver : Failed to read HTTP message: org.springframework.http.converter.HttpMessageNotReadableException:
     * JSON parse error: Can not deserialize instance of java.lang.Integer[] out of VALUE_STRING token;
     * nested exception is com.fasterxml.jackson.databind.JsonMappingException: Can not deserialize instance of java.lang.Integer[] out of VALUE_STRING token
     * at [Source: java.io.PushbackInputStream@4204a07; line: 2, column: 15] (through reference chain: com.tangcheng.learning.web.dto.req.SayHelloRequest["classIds"])
     *
     * @param e
     * @return
     */
    @ExceptionHandler(JSONException.class)
    public ResponseEntity<?> handleJsonException(JSONException e) {
        log.error("MethodArgumentNotValidException.class.msg:{}", e);
        StringBuilder builder = new StringBuilder();
        if (e.getCause() != null) {
            builder.append(e.getCause().getMessage())
                    .append(System.lineSeparator());
        }
        builder.append(e.getMessage());
        return ResponseEntity.badRequest().body(builder.toString());
    }


}
