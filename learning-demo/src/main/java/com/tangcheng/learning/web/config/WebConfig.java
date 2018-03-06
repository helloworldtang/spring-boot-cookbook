package com.tangcheng.learning.web.config;

import com.tangcheng.learning.web.interceptor.MyInterceptor1;
import com.tangcheng.learning.web.interceptor.MyInterceptor2;
import com.tangcheng.learning.web.interceptor.MyInterceptor3;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author tangcheng
 * 2018/03/06
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * MyInterceptor2中的preHandle返回false如下执行情况。并且相关rest请求页面不会正常响应，即页面会白屏
     * c.t.learning.interceptor.MyInterceptor1  : >>>MyInterceptor1>>>>>>>preHandle在请求处理之前进行调用（Controller方法调用之前）
     * c.t.learning.interceptor.MyInterceptor2  : >>>MyInterceptor2>>>>>>>preHandle在请求处理之前进行调用（Controller方法调用之前）
     * c.t.learning.interceptor.MyInterceptor1  : >>>MyInterceptor1>>>>>>>afterCompletion.在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     * <p>
     * 拦截器中的preHandle都返回true时，如下执行顺序
     * <p>
     * [nio-8080-exec-1] c.t.l.web.interceptor.MyInterceptor1     : >>>MyInterceptor1>>>>>>>preHandle在请求处理之前进行调用（Controller方法调用之前）
     * [nio-8080-exec-1] c.t.l.web.interceptor.MyInterceptor2     : >>>MyInterceptor2>>>>>>>preHandle在请求处理之前进行调用（Controller方法调用之前）
     * [nio-8080-exec-1] c.t.l.web.interceptor.MyInterceptor3     : >>>MyInterceptor3>>>>>>>preHandle在请求处理之前进行调用（Controller方法调用之前）
     * [nio-8080-exec-1] c.t.l.web.interceptor.MyInterceptor3     : >>>MyInterceptor3>>>>>>>postHandle请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）.
     * [nio-8080-exec-1] c.t.l.web.interceptor.MyInterceptor2     : >>>MyInterceptor2>>>>>>>postHandle请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）.
     * [nio-8080-exec-1] c.t.l.web.interceptor.MyInterceptor1     : >>>MyInterceptor1>>>>>>>postHandle请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）.
     * [nio-8080-exec-1] c.t.l.web.interceptor.MyInterceptor3     : >>>MyInterceptor3>>>>>>>afterCompletion.在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     * [nio-8080-exec-1] c.t.l.web.interceptor.MyInterceptor2     : >>>MyInterceptor2>>>>>>>afterCompletion.在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     * [nio-8080-exec-1] c.t.l.web.interceptor.MyInterceptor1     : >>>MyInterceptor1>>>>>>>afterCompletion.在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     * http://www.cnblogs.com/softidea/p/6064064.html
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new MyInterceptor1());
        registry.addInterceptor(new MyInterceptor2());
        registry.addInterceptor(new MyInterceptor3());
    }
}
