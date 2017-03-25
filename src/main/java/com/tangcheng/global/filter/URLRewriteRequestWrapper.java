package com.tangcheng.global.filter;

import com.tangcheng.rest.RewriteServletPathController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by tang.cheng on 2016/9/27.
 */
public class URLRewriteRequestWrapper extends HttpServletRequestWrapper {

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public URLRewriteRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * rewrite ServletPath
     *
     * 假定工程名称为projects,你在浏览器中输入请求路径：
     http://127.0.0.1:8080/projects/pages/newForm.jsp
     则执行下面向行代码后打印出如下结果：
     1、 System.out.println(request.getContextPath());
     打印结果：/projects
     2、System.out.println(request.getServletPath());
     打印结果：/pages/newForm.jsp
     3、 System.out.println(request.getRequestURI());
     打印结果：/projects/pages/newForm.jsp
     4、 System.out.println(request.getRealPath("/"));
     JSP servlet API提供了getRealPath(path)方法，返回给定虚拟路径的真实路径，如果转换错误，则返回null。
     打印结果：C:\Tomcat5.0\webapps\projects\test
     * @return
     */
    @Override
    public String getServletPath() {
        String servletPath = super.getServletPath();
        if (servletPath.equals(RewriteServletPathController.CHANGE_USER_URL)) {
            String userId = this.getParameter("userId");
            return servletPath + "/" + userId;
        }
        return servletPath;
    }
}
