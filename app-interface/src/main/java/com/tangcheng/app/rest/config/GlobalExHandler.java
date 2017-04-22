package com.tangcheng.app.rest.config;

import com.tangcheng.app.domain.exception.BizException;
import com.tangcheng.app.domain.errorcode.GlobalCode;
import com.tangcheng.app.domain.vo.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class GlobalExHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExHandler.class);

    /**
     * 专门处理业务异常
     *
     * @param bizEx
     * @return
     */
    @ExceptionHandler(BizException.class)
    public ResultData<?> handleAllException(BizException bizEx) {
        LOGGER.error(bizEx.getMessage(), bizEx);
        return new ResultData<>(bizEx.getBizError());
    }


    @ExceptionHandler(BindException.class)
    public ResponseEntity handleBindException(BindException e) {
        LOGGER.error(e.getMessage(), e);
        StringBuilder result = new StringBuilder();
        for (FieldError fieldError : e.getFieldErrors()) {
            result.append(fieldError.getField()).append(":").
                    append(fieldError.getDefaultMessage()).
                    append(System.getProperty("line.separator"));
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
        LOGGER.error(e.getMessage(), e);
        if (e instanceof SQLException || e instanceof DataAccessException) {
            return new ResultData<>(GlobalCode.FAIL, "服务器好像开小差了，等会再试下:-) ");
        }
        return new ResultData<>(GlobalCode.FAIL, e.getMessage());
    }

}
