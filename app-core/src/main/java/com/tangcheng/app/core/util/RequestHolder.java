package com.tangcheng.app.core.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tang.cheng on 2016/12/2.
 */
public class RequestHolder {
    /**
     * 文件上传，form表单的enctype类型为”multipart/form-data”，
     * spring mvc对文件上传的处理类实际却为spring-mvc.xml文件中配置的CommonsMultipartResolver，
     * 该类先判断当前请求是否为multipart类型，如果是的话，将request对象转为MultipartHttpServletRequet,
     * 相关的源码见DisptcherServlet
     * <p>
     * RequestContextHolder中的request又是从哪来的呢？
     * 继续翻看DispatcherServlet的源码，从其父类FrameworkServlet中找到的processRequest()的相关方法源码
     * <p>
     * <p>
     * 从这里可以看到，initContextHolder()方法中完成了RequestContextHolder的requestAttributes设置，
     * 而doService()在这之后调用，DispatcherServlet中的processRequest()方法即在doService()之中，
     * 所以从RequestContextHolder中获取到的就是原来的RequestFacade对象，而不是经过spring mvc处理之后的MultipartHttpServletRequest对象，
     * 其后果就是，从RequestContextHolder获取request后，无法直接通过getParameter()获取参数值
     * <p>
     * <p>
     * 建议：获取上传操作Request中的参数时
     * 直接将HttpServletRequest作为spring mvc的方法入参，即可以正确获取参数值。
     *
     * @return
     */
    public static HttpServletRequest getRequestFacade() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }

    public static HttpServletResponse getResponseFacade() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletWebRequest servletWebRequest = (ServletWebRequest) requestAttributes;
        return servletWebRequest.getResponse();
    }


    public static String getLastAccessUrl() {
        HttpServletRequest httpServletRequest = getRequestFacade();
        String requestURI = httpServletRequest.getRequestURI();
        String queryString = httpServletRequest.getQueryString();
        if (queryString == null) {
            return requestURI;
        }
        return requestURI + "?" + queryString;

    }


}
