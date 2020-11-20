package com.tcm.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.formLogin().loginPage("/login").defaultSuccessUrl("/inpg01").failureUrl("/login-error").permitAll();
    	http.authorizeRequests().antMatchers("/css/**", "/images/**", "/js/**").permitAll().anyRequest().authenticated();
    }
}