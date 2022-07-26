package com.tangcheng.learning.adapter.web.config;

import com.alibaba.fastjson.JSONException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author tangcheng
 * 2018/05/02
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


//    @ModelAttribute
//    public void initUser(ModelMap modelMap) {
//        User user = new User("tom", (byte) 123);
//        modelMap.put("user", user);
//    }

    /**
     * javax.validation.Valid对Request参数时行校验，不合法会报org.springframework.validation.BindException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity handleBindException(BindException e) {
        log.error("{}.msg:{}", e.getClass().getName(), e);
        StringBuilder result = new StringBuilder();
        for (FieldError fieldError : e.getFieldErrors()) {
            result.append(fieldError.getField()).append(":").
                    append(fieldError.getDefaultMessage()).
                    append(System.lineSeparator());
        }
        return ResponseEntity.badRequest().body(result.toString());
    }

    /**
     * Request Body 必填。但实际传参时不传
     * org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
     * if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
     * throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
     * }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("{}.msg:{}", e.getClass().getName(), e);
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
     * 使用@RequestHeader 且字段为必填时，报此错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServletRequestBindingException.class)
    public ResponseEntity<String> exception(ServletRequestBindingException e) {
        log.error("{}.msg:{}", e.getClass().getName(), e);
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
     * at [Source: java.io.PushbackInputStream@4204a07; line: 2, column: 15] (through reference chain: com.tangcheng.learning.web.dto.req.SayHelloReq["classIds"])
     * 另一种场景：
     * (1)fastjson:
     * com.alibaba.fastjson.JSONException: int value overflow, field : userId
     * at com.alibaba.fastjson.serializer.IntegerCodec.deserialze(IntegerCodec.java:84) ~[fastjson-1.2.31.jar:?]
     * at com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer.parseField(DefaultFieldDeserializer.java:71) ~[fastjson-1.2.31.jar:?]
     * at com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.parseField(JavaBeanDeserializer.java:828) ~[fastjson-1.2.31.jar:?]
     * Caused by: java.lang.NumberFormatException: 111111111111111111111111111111111111111111
     * at com.alibaba.fastjson.parser.JSONLexerBase.intValue(JSONLexerBase.java:1062) ~[fastjson-1.2.31.jar:?]
     * at com.alibaba.fastjson.serializer.IntegerCodec.deserialze(IntegerCodec.java:82) ~[fastjson-1.2.31.jar:?]
     *
     * @param e
     * @return
     */
    @ExceptionHandler(JSONException.class)
    public ResponseEntity<?> handleJsonException(JSONException e) {
        log.error("{}.msg:{}", e.getClass().getName(), e);
        StringBuilder builder = new StringBuilder();
        if (e.getCause() != null) {
            builder.append(e.getCause().getMessage())
                    .append(System.lineSeparator());
        }
        builder.append(e.getMessage());
        return ResponseEntity.badRequest().body(builder.toString());
    }

    /**
     * org.springframework.http.converter.GenericHttpMessageConverter#read(java.lang.reflect.Type, java.lang.Class, org.springframework.http.HttpInputMessage)
     * T read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException;
     * <p>
     * HttpInputMessage转换成JSON对象时，失败：
     * org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Numeric value (1111111111111111111111111111111111111111) out of range of int (-2147483648 - 2147483647)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("{}.msg:{}", e.getClass().getName(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("{}.msg:{}", e.getClass().getName(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSQLException(IllegalArgumentException e) {
        log.error("【数据库报错了】{}.msg:{}", e.getClass().getName(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(IllegalArgumentException e) {
        log.error("【数据库报错了】{}.msg:{}", e.getClass().getName(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(IllegalArgumentException e) {
        log.error("{}.msg:{}", e.getClass().getName(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
