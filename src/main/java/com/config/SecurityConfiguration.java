package com.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by tang.cheng on 2016/12/12.
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity //As of Spring Security 4.0, @EnableWebMvcSecurity is deprecated. The replacement is @EnableWebSecurity which will determine adding the Spring MVC features based upon the classpath.
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN", "USER")
                .and()
                .withUser("user").password("user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http.authorizeRequests().anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
                .and()
                .logout().permitAll();*/


        http.authorizeRequests()
                .antMatchers("/resources/**", "/signup", "/about").permitAll()
                .antMatchers("/css/**", "/index").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")//Any URL that starts with "/admin/" will be restricted to users who have the role "ROLE_ADMIN". You will notice that since we are invoking the hasRole method we do not need to specify the "ROLE_" prefix.
                .antMatchers("/db/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_DBA')")
                .antMatchers("/flyway").permitAll()
                .anyRequest().authenticated()//Any URL that has not already been matched on only requires that the user be authenticated
                .and()
                .formLogin().loginPage("/login")
                .failureUrl("/login?error").permitAll()
                .and()
                .logout().permitAll();

    }
}
