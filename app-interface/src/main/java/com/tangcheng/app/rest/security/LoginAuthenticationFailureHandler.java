package com.tangcheng.app.rest.security;

import com.tangcheng.app.domain.exception.VerifyCodeException;
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
    private static final String PASS_ERROR_URL = "/login?error";
    private static final String KAPTCHA_ERROR_URL = "/login?verification";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        if (exception instanceof VerifyCodeException) {
            getRedirectStrategy().sendRedirect(request, response, KAPTCHA_ERROR_URL);
        } else {
            getRedirectStrategy().sendRedirect(request, response, PASS_ERROR_URL);
        }
    }
}
