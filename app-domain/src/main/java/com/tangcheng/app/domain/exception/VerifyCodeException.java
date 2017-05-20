package com.tangcheng.app.domain.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by tangcheng on 5/21/2017.
 */
public class VerifyCodeException extends AuthenticationException {

    public VerifyCodeException(String msg) {
        super(msg);
    }

}
