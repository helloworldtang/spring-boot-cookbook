package com.tangcheng.app.api.rest.filter;

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
     * 这个class是一个tmp，不涉及业务逻辑
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
        Vector<String> vector = new Vector<>(params.keySet());
        return vector.elements();
    }

    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }


}
