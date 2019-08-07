package com.tangcheng.app.api.rest.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by tang.cheng on 2016/9/27.
 */
public class RewriteServletPathFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RewriteServletPathFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info(this.getClass().getName()+" ");
        URLRewriteRequestWrapper urlRewriteRequestWrapper = new URLRewriteRequestWrapper((HttpServletRequest) request);
        chain.doFilter(urlRewriteRequestWrapper, response);
    }

    @Override
    public void destroy() {

    }
}
