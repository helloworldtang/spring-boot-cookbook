package com.config.datasource.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidConfiguration {
    @Bean
    public ServletRegistrationBean druidServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }

    //这两种init DataSource的方式都可以
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
//    @ConfigurationProperties(prefix = "spring.druid.datasource", locations = {"classpath:config/druid.properties"})
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        try {
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}