package com.tangcheng.app.api.rest.security;

import com.tangcheng.app.domain.exception.CaptchaException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tangcheng on 5/21/2017.
 */
public class LoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    public static final String PASS_ERROR_URL = "/login?error";
    public static final String CODE_ERROR_URL = "/login?code";
    public static final String EXPIRED_URL = "/login?expire";
    public static final String DISABLED_URL = "/login?disabled";
    public static final String LOCKED_URL = "/login?locked";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if (exception instanceof CaptchaException) {
            getRedirectStrategy().sendRedirect(request, response, CODE_ERROR_URL);
        } else {
            getRedirectStrategy().sendRedirect(request, response, PASS_ERROR_URL);
        }
    }
}
