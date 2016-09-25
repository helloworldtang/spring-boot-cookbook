package com.config;

import com.global.filter.AddExtraToParamsFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MyWorld on 2016/9/25.
 */
@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AddExtraToParamsFilter());
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }
}
