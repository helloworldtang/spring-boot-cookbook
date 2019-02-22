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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

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


    @ExceptionHandler(BindException.class)
    public ResponseEntity handleBindException(BindException bindEx) {
        log.error("BindException.class {},{}", RequestHolder.getLastAccessUrl(), bindEx.getMessage(), bindEx);
        StringBuilder result = new StringBuilder();
        for (FieldError fieldError : bindEx.getFieldErrors()) {
            result.append(fieldError.getField()).append(":").
                    append(fieldError.getDefaultMessage()).
                    append(System.lineSeparator());
        }
        return ResponseEntity.badRequest().body(result.toString());
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
