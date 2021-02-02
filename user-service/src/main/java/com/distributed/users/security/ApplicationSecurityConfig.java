package com.distributed.users.security;

import com.distributed.users.JWT.JwtConfig;
import com.distributed.users.JWT.JwtTokenFilterConfigurer;
import com.distributed.users.JWT.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.crypto.SecretKey;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    private  JwtTokenProvider jwtTokenProvider;


    @Autowired
    public ApplicationSecurityConfig(SecretKey secretKey,
                                     JwtConfig jwtConfig,
                                     JwtTokenProvider jwtTokenProvider
                                     ) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers(HttpMethod.POST,"/users").permitAll()
                .antMatchers(HttpMethod.GET,"/users/*/token").permitAll()
                                .anyRequest()
                .authenticated();
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    }





}
