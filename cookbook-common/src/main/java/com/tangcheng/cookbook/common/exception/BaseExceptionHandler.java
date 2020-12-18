package com.tangcheng.cookbook.common.exception;

import com.alibaba.fastjson.JSONException;
import com.tangcheng.app.core.util.RequestHolder;
import com.tangcheng.cookbook.common.domain.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * spring-boot-cookbook
 *
 * @author: tangcheng
 * @date: 2020/12/18 5:43 下午
 * @see
 * @since
 */
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResultDTO<String> handlerThrowable(Throwable e) {
        return handleThrowable(e);
    }

    public ResultDTO<String> handleThrowable(Throwable e) {
        HttpServletResponse httpServletResponse = RequestHolder.getResponseFacade();
        if (e instanceof BizErrorException) {
            if (e instanceof BizErrorStatus200Exception) {
                httpServletResponse.setStatus(HttpStatus.OK.value());
            }

            if (e instanceof NoPrintStackBaseException) {
                return handleNoPrintStackBaseException((NoPrintStackBaseException) e);
            } else if (e instanceof NoPrintStackAndStatus200Exception) {
                return handleNoPrintStackAndStatus200Exception((NoPrintStackAndStatus200Exception) e);
            } else {
                log.warn(" handleThrowable {},msg:{}, {}", RequestHolder.getLastAccessUrl(), e.getMessage(), e.getClass().getName(), e);
            }
            BizErrorException bizErrorException = (BizErrorException) e;
            return ResultDTO.fail(bizErrorException.getBizError());
        }

        if (e instanceof BindException) {
            log.warn(" handleThrowable {},msg:{}, {}", RequestHolder.getLastAccessUrl(), e.getMessage(), e.getClass().getName());
            String errorMsg = ((BindException) e).getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(System.lineSeparator()));
            return ResultDTO.fail(errorMsg);
        }

        if (e instanceof MethodArgumentNotValidException) {
            log.warn(" handleThrowable {},msg:{}, {}", RequestHolder.getLastAccessUrl(), e.getMessage(), e.getClass().getName());
            String errorMsg = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(System.lineSeparator()));
            return ResultDTO.fail(errorMsg);
        }

        if (e instanceof SQLException || e instanceof DataAccessException || e instanceof IOException) {
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            log.error("【数据库报错了】联系开发解决。出错了！ handleThrowable {},msg:{}, {}", RequestHolder.getLastAccessUrl(), e.getMessage(), e.getClass().getName(), e);
            return ResultDTO.fail("联系开发解决。出错了！");
        }
        if (e instanceof ServletRequestBindingException) {
            log.warn(" handleThrowable {},msg:{}, {}", RequestHolder.getLastAccessUrl(), e.getMessage(), e.getClass().getName());
            String errorMsg = getServletRequestBindingExceptionMsg((ServletRequestBindingException) e);
            return ResultDTO.fail(errorMsg);
        }

        if (e instanceof JSONException) {
            log.warn(" handleThrowable {},msg:{}, {}", RequestHolder.getLastAccessUrl(), e.getMessage(), e.getClass().getName(), e);
            String errorMsg = handleJsonException((JSONException) e);
            return ResultDTO.fail(errorMsg);
        }

        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error(" handleThrowable {},msg:{}, {}", RequestHolder.getLastAccessUrl(), e.getMessage(), e.getClass().getName(), e);
        return ResultDTO.fail(e.getMessage());
    }

    /**
     * 解决pinpoint中因为抛异常httpStatus=500造成告警过多的问题
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Status200Exception.class)
    public ResultDTO<String> handleStatus200Exception(Status200Exception e) {
        log.warn(" Status200Exception {},msg:{}, {}", RequestHolder.getLastAccessUrl(), e.getMessage(), e);
        return ResultDTO.fail(e.getMessage());
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BizErrorStatus200Exception.class)
    public ResultDTO handleBizErrorStatus200Exception(BizErrorStatus200Exception e) {
        log.warn(" BizErrorStatus200Exception {},msg:{},{}", RequestHolder.getLastAccessUrl(), e.getMessage(), e);
        return ResultDTO.fail(e.getBizError());
    }


    @ExceptionHandler(NoPrintStackBaseException.class)
    public ResultDTO<String> handleNoPrintStackBaseException(NoPrintStackBaseException e) {
        log.warn(" NoPrintStackBaseException {},msg:{}", RequestHolder.getLastAccessUrl(), e.getMessage());
        RequestHolder.getResponseFacade().setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResultDTO.fail(e.getBizError());
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoPrintStackAndStatus200Exception.class)
    public ResultDTO<String> handleNoPrintStackAndStatus200Exception(NoPrintStackAndStatus200Exception e) {
        log.warn(" NoPrintStackAndStatus200Exception {},msg:{}", RequestHolder.getLastAccessUrl(), e.getMessage());
        RequestHolder.getResponseFacade().setStatus(HttpStatus.OK.value());
        return ResultDTO.fail(e.getBizError());
    }


    private String getServletRequestBindingExceptionMsg(ServletRequestBindingException e) {
        String errorMsg = e.getMessage();
        if (StringUtils.isBlank(errorMsg)) {
            return "";
        }
        String[] split = errorMsg.split("'");
        if (split.length > 2) {
            return String.join(",", split[0], split[1]);
        }
        return errorMsg;
    }


    private String handleJsonException(JSONException e) {
        StringBuilder builder = new StringBuilder();
        if (e.getCause() != null) {
            builder.append(e.getCause().getMessage())
                    .append(System.lineSeparator());
        }
        builder.append(e.getMessage());
        return builder.toString();
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SQLException.class)
    public ResultDTO<String> handleNoPrintStackAndStatus200Exception(SQLException e) {
        log.warn(" 【数据库报错了】联系开发解决。出错了！{} {},msg:{}", e.getClass().getCanonicalName(), RequestHolder.getLastAccessUrl(), e.getMessage());
        RequestHolder.getResponseFacade().setStatus(HttpStatus.OK.value());
        return ResultDTO.fail("联系开发解决。出错了！");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(DataAccessException.class)
    public ResultDTO<String> handleNoPrintStackAndStatus200Exception(DataAccessException e) {
        log.warn(" 【数据库报错了】联系开发解决。出错了！{} {},msg:{}", e.getClass().getCanonicalName(), RequestHolder.getLastAccessUrl(), e.getMessage());
        RequestHolder.getResponseFacade().setStatus(HttpStatus.OK.value());
        return ResultDTO.fail("联系开发解决。出错了！");
    }




}
