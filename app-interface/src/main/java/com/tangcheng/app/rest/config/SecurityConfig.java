package com.tangcheng.app.rest.config;

import com.tangcheng.app.rest.filter.LoginAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Created by tang.cheng on 2016/12/12.
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
//As of Spring Security 4.0, @EnableWebMvcSecurity is deprecated. The replacement is @EnableWebSecurity which will determine adding the Spring MVC features based upon the classpath.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("ADMIN", "USER")
//                .and()
//                .withUser("user").password("user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/login", "/verification.jpg").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")//Any URL that starts with "/admin/" will be restricted to users who have the role "ROLE_ADMIN". You will notice that since we are invoking the hasRole method we do not need to specify the "ROLE_" prefix.
                .antMatchers("/db/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_DBA')")
                .antMatchers("/flyway", "/tx/**", "/user/**", "/etag/**").permitAll()
                .anyRequest().fullyAuthenticated()//Any URL that has not already been matched on only requires that the user be authenticated
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .and()
                .rememberMe()//登陆请求必须包含一个名为remember-me的参数
                .tokenValiditySeconds(2419200)//four week 2419200s
                .key("cookbookKey")//存储在cookies中包含用户名，密码，过期时间和一个私钥---在写入cookie前都进行了MD5 hash
                .and()
                .logout()
                .logoutSuccessUrl("/login");
        http.headers().cacheControl().disable();

        LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());

        http.addFilterBefore(filter, BasicAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //allow Swagger URL to be accessed without authentication
        web.ignoring().antMatchers( //"/v2/api-docs",//change to /swagger and custom the groupName
                "/swagger",// Resolve conflicts version number
                "/swagger-resources/configuration/ui",//用来获取支持的动作
                "/swagger-resources",//用来获取api-docs的URI
                "/swagger-resources/configuration/security",//安全选项
                "/webjars/**",///swagger-ui.html使用的一些资源文件在webjars目录下。eg:http://localhost/webjars/springfox-swagger-ui/images/logo_small.png
                "/swagger-ui.html",
                "/h2/**" // h2/query.jsp?jsessionid=f2e1c5f5748414b8b4f8e844f74ef99d.The H2 database provides a browser-based console that Spring Boot can auto-configure for you.
        );
    }

}
