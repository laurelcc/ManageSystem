package com.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

/**
 * Created by soong on 2017/7/30.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource ds;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(ds)
                .withDefaultSchema();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .and()
                .httpBasic();


//        LogoutHandler
//        PersistentTokenBasedRememberMeServices
//        TokenBasedRememberMeServices
//        CookieClearingLogoutHandler
//        CsrfLogoutHandler
//        SecurityContextLogoutHandler

//        LogoutSuccessHandler
//        SimpleUrlLogoutSuccessHandler
//        HttpStatusReturningLogoutSuccessHandler
    }

//    @Bean
//    public CustomDataUserDetailsService customUserDetailsService() {
//        CustomDataUserDetailsService service = new CustomDataUserDetailsService();
//        return service;
//    }

//    public CustomAuthenticationProvider customAuthenticationProvider() {
//        CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
//        return provider;
//    }
//

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}