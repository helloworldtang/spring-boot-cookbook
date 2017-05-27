package com.tangcheng.app.rest.config;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonpHttpMessageConverter4;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Created by tang.cheng on 2016/12/12.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    /**
     * org.springframework.beans.factory.UnsatisfiedDependencyException:
     * Error creating bean with name 'fastJsonpResponseBodyAdvice' defined in URL [jar:file:/C:/repository/com/alibaba/fastjson/1.2.28/fastjson-1.2.28.jar!/com/alibaba/fastjson/support/spring/FastJsonpResponseBodyAdvice.class]:
     * Unsatisfied dependency expressed through constructor parameter 0: No qualifying bean of type [[Ljava.lang.String;] found for dependency [java.lang.String[]]:
     * expected at least 1 bean which qualifies as autowire candidate for this dependency.
     * Dependency annotations: {}; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
     * No qualifying bean of type [[Ljava.lang.String;] found for dependency [java.lang.String[]]:
     * expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {}
     * at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:749)
     * 解决办法：https://github.com/alibaba/fastjson/wiki/FastJsonpHttpMessageConverter4_CN
     *
     * @return
     */
    @Bean
    public FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice() {
        return new FastJsonpResponseBodyAdvice("callback", "jsonp");
    }

    @Bean
    public FastJsonpHttpMessageConverter4 fastJsonpHttpMessageConverter4() {
        FastJsonpHttpMessageConverter4 converter4 = new FastJsonpHttpMessageConverter4();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializeFilters(new ValueFilter() {
            @Override
            public Object process(Object o, String s, Object source) {
                if (source == null) {
                    return "";
                }
                return source;
            }
        });
        converter4.setFastJsonConfig(fastJsonConfig);
        return converter4;
    }

    @Bean
    public LocaleResolver localResolver() {
        return new SessionLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
        changeInterceptor.setParamName("lang");
        return changeInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


}
