package com.tangcheng.app.rest.filter;

import com.google.code.kaptcha.Constants;
import com.tangcheng.app.rest.security.LoginAuthenticationFailureHandler;
import com.tangcheng.app.domain.exception.VerifyCodeException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tangcheng on 5/21/2017.
 */
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public LoginAuthenticationFilter() {
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        this.setAuthenticationFailureHandler(new LoginAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String verification = request.getParameter("verification");
        String captcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if (!captcha.contentEquals(verification)) {
            throw new VerifyCodeException("verify code not matched!");
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authRequest
                = new UsernamePasswordAuthenticationToken(username, password);

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
