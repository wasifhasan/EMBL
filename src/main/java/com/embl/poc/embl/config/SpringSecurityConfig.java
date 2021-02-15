package com.embl.poc.embl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * The Configuration class to implement spring security
 * 
 * @author Syed.Hasan
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	 // Create 2 users for demo
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
        auth.inMemoryAuthentication()
                .withUser("normaluser").password("{noop}password").roles("USER")
                .and()
                .withUser("adminuser").password("{noop}password").roles("USER", "ADMIN");

    }

    // Secure the endpoints with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.headers().frameOptions().disable();
        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/embl/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/embl").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/embl/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/embl/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}
