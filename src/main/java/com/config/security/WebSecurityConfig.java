package com.config.security;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
<<<<<<< HEAD
import org.springframework.security.access.annotation.Secured;
=======
import org.springframework.context.annotation.Configuration;
>>>>>>> b0ca07ef4e375cf7a993ed341c2c9427d81fcb92
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CsrfLogoutHandler;


/**
 * Created by soong on 2017/7/30.
 */
@Configuration
@EnableWebSecurity
<<<<<<< HEAD
//@EnableGlobalMethodSecurity(securedEnabled = true)
=======
@EnableGlobalMethodSecurity(securedEnabled = true)
>>>>>>> b0ca07ef4e375cf7a993ed341c2c9427d81fcb92
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, CustomDataUserDetailsService service) throws Exception {
        auth.userDetailsService(service);
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

    @Bean
    public CustomDataUserDetailsService customUserDetailsService() {
        CustomDataUserDetailsService service = new CustomDataUserDetailsService();
        return service;
    }

//    public CustomAuthenticationProvider customAuthenticationProvider() {
//        CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
//        return provider;
//    }
//
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}