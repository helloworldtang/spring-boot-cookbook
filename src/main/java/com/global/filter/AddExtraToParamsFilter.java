package com.global.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MyWorld on 2016/9/25.
 */
public class AddExtraToParamsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String pid = httpServletRequest.getHeader("_pid");
        if (pid != null) {
            params.put("pid", new String[]{pid});
        }
        request = new ParameterRequestWrapper(httpServletRequest, params);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
