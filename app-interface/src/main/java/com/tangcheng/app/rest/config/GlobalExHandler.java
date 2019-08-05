package com.tangcheng.app.rest.config;

import com.tangcheng.app.core.util.RequestHolder;
import com.tangcheng.app.domain.errorcode.GlobalCode;
import com.tangcheng.app.domain.exception.BizException;
import com.tangcheng.app.domain.vo.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * Created by MyWorld on 2016/9/23.
 */
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Slf4j
public class GlobalExHandler {

    /**
     * 专门处理业务异常
     *
     * @param bizEx
     * @return
     */
    @ExceptionHandler(BizException.class)
    public ResultData<?> handleAllException(BizException bizEx) {
        log.error("BizException.class {},{}", RequestHolder.getLastAccessUrl(), bizEx.getMessage(), bizEx);
        return ResultData.builder().detail(bizEx).build();
    }

    /**
     * javax.validation.Valid对Request参数时行校验，不合法会报org.springframework.validation.BindException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity handleBindException(BindException e) {
        log.warn("{},msg:{}", RequestHolder.getLastAccessUrl(), e.getMessage());
        String bindExceptionMsg = e.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(System.lineSeparator()));
        return ResponseEntity.badRequest().body(bindExceptionMsg);
    }

    /**
     * Request Body 必填。但实际传参时不传
     * org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
     * if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
     * throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
     * }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("{},msg:{}", RequestHolder.getLastAccessUrl(), e.getMessage());
        String bindExceptionMsg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(System.lineSeparator()));
        return ResponseEntity.badRequest().body(bindExceptionMsg);
    }

    /**
     * org.springframework.web.bind.ServletRequestBindingException: Missing request header 'userId' for method parameter of type Integer
     * 使用@RequestHeader 且字段为必填时，报此错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<String> handleServletRequestBindingException(ServletRequestBindingException e) {
        log.warn("{},msg:{}", RequestHolder.getLastAccessUrl(), e.getMessage());
        String servletRequestBindingExceptionMsg = getServletRequestBindingExceptionMsg(e);
        return ResponseEntity.badRequest().body(servletRequestBindingExceptionMsg);
    }


    private String getServletRequestBindingExceptionMsg(ServletRequestBindingException e) {
        String[] split = e.getMessage().split("'");
        if (split.length > 2) {
            return String.join(",", split[0], split[1]);
        }
        return e.getMessage();
    }


    /**
     * 处理未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultData<String> throwable(Exception e) {
        log.error("Exception.class {},{}", RequestHolder.getLastAccessUrl(), e.getMessage(), e);
        if (e instanceof SQLException || e instanceof DataAccessException) {
            return ResultData.<String>builder().bizError(GlobalCode.FAIL.setMsg("哎呀，好像有人开小差了！等会再试下哦:-) ")).build();
        }
        return ResultData.<String>builder().bizError(GlobalCode.FAIL.setMsg(e.getMessage())).build();
    }

}
