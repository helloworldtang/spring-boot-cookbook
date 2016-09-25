package com.global.filter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

/**
 * Created by MyWorld on 2016/9/25.
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     * http://www.cnblogs.com/softidea/p/5903873.html
     * http://www.cnblogs.com/softidea/p/5904831.html
     */
    private Map<String, String[]> params;

    public ParameterRequestWrapper(HttpServletRequest request, Map<String, String[]> params) {
        super(request);
        this.params = params;
        addHeaderInfoToParams(request);
    }

    private void addHeaderInfoToParams(HttpServletRequest request) {
        request.getHeader("_pid");

    }

    @Override
    public String getParameter(String name) {
        String[] values = getParameterValues(name);
        if (values == null || values.length == 0) {
            return null;
        } else {
            return values[0];
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return new Vector(params.keySet()).elements();
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = params.get(name);
        if (values == null) {
            return null;
        } else {
            return values;
        }
    }


}
