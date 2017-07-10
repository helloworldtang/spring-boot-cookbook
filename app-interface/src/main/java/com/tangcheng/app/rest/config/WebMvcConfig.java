package com.tangcheng.app.rest.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonpHttpMessageConverter4;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.Charset;
import java.util.ArrayList;
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
//        http://www.cnblogs.com/sunp823/p/5601397.html
//        http://blog.csdn.net/my_god_sky/article/details/53385246
        FastJsonpHttpMessageConverter4 converter4 = new FastJsonpHttpMessageConverter4();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        converter4.setSupportedMediaTypes(supportedMediaTypes);
        converter4.setDefaultCharset(Charset.forName("utf-8"));

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
