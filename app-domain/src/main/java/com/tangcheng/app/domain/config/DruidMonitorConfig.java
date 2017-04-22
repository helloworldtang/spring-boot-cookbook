package com.tangcheng.app.domain.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tang.cheng on 2017/4/17.
 * https://github.com/alibaba/druid
 */
@Configuration
public class DruidMonitorConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
/*      此servlet提供展示druid统计数据的界面。
        这个StatViewServlet的用途包括：
        提供监控信息展示的html页面
        提供监控信息的JSON API
         https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_StatViewServlet%E9%85%8D%E7%BD%AE */
        StatViewServlet servlet = new StatViewServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(servlet, "/druid/*");
//        根据配置中的url-pattern来访问内置监控页面，如果是上面的配置，内置监控页面的首页是/druid/index.html

/*      deny优先于allow，如果在deny列表中，就算在allow列表中，也会被拒绝。
        如果allow没有配置或者为空，则允许所有访问。
        由于匹配规则不支持IPV6，配置了allow或者deny之后，会导致IPV6无法访问。*/
//        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_ALLOW, "127.0.0.1,192.168.1.10/24");
//        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_DENY, "192.168.1.8");
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_USERNAME, "admin");
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_PASSWORD, "admin-pwd");

        //在StatViewServlet输出的html页面中，有一个功能是Reset All，执行这个操作之后，会导致所有计数器清零，重新计数。
        // 你可以通过配置参数关闭它。
        // 此处是开启这个功能
        registrationBean.addInitParameter(StatViewServlet.PARAM_NAME_RESET_ENABLE, Boolean.TRUE.toString());
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter() {
/*        Web关联监控配置：
        WebStatFilter用于采集web-jdbc关联监控的数据。为StatViewServlet准备用来展示的数据*/
        WebStatFilter filter = new WebStatFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.addUrlPatterns("/*");
//      经常需要排除一些不必要的url，比如*.js,/jslib等等。配置在init-param中。比如：
        registrationBean.addInitParameter(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//      druid 0.2.7版本开始支持profile，配置profileEnable能够监控单个url调用的sql列表。
        registrationBean.addInitParameter(WebStatFilter.PARAM_NAME_PROFILE_ENABLE, Boolean.TRUE.toString());
        return registrationBean;
    }


    /*
    以下配置，提供StatViewServlet UI中"spring监控"项的数据
     Spring关联监控配置：
     监听Spring
     1.定义拦截器
     2.定义切入点
     3.定义通知类
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    @Bean
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
        String servicePatterns = "com.tangcheng.service.*";
        String mapperPatterns = "com.tangcheng.app.domain.mapper.*";
        druidStatPointcut.setPatterns(servicePatterns, mapperPatterns);
        return druidStatPointcut;
    }

    @Bean
    public Advisor druidStatAdvisor() {
        return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }
}

