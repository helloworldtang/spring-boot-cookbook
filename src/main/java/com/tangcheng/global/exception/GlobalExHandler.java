package com.tangcheng.global.exception;

import com.tangcheng.global.domain.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by MyWorld on 2016/9/23.
 */
@RestControllerAdvice
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


    /**
     * 处理未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultData<String> throwable(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return new ResultData<>(GlobalCode.FAIL, e.getMessage());
    }

}
