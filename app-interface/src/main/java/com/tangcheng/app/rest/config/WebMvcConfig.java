package com.tangcheng.app.rest.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Date;
import java.util.List;

/**
 * Created by tang.cheng on 2016/12/12.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        https://spring.io/guides/gs/securing-web/
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurePathMatch(PathMatchConfigurer configurer)函数让开发人员可以根据需求定制URL路径的匹配规则。
//        configurer.setUseSuffixPatternMatch(false)表示设计人员希望系统对外暴露的URL不会识别和匹配.*后缀。
// 在这个例子中，就意味着Spring会将793059909@qq.com当做一个{email}参数传给Controller层接口
//        configurer.setUseTrailingSlashMatch(true)表示系统不区分URL的最后一个字符是否是斜杠/。
// 在这个例子中，就意味着http://localhost:8080/email/793059909@qq.com和http://localhost:8080/email/793059909@qq.com/含义相同

//        后缀模式匹配模式：
        //如果没有setUseSuffixPatternMatch(false)的设置，则http://localhost/hello和http://localhost/hello.html会访问相同的接口，
        //因为/hello是可以匹配到/hello.*的，/hello.test,/hello.world都可以访问到/hello接口

//        末尾斜杠匹配
//        setUseTrailingSlashMatch (boolean useSuffixPatternMatch)：
//        设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；
//        当此参数设置为true的会后，那么地址/user，/user/都能正常访问。
//        当此参数设置为false的时候，那么就只能访问/user了
        configurer.setUseSuffixPatternMatch(false)
                .setUseTrailingSlashMatch(true);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter httpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteEnumUsingToString,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        fastJsonConfig.setSerializeFilters(new ValueFilter() {
            @Override
            public Object process(Object o, String s, Object source) {
                if (source == null) {
                    return "";
                }
                if (source instanceof Date) {
                    return ((Date) source).getTime();
                }
                return source;
            }
        });
        httpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(httpMessageConverter);
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
