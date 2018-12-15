package com.config;

import com.utils.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public MappedInterceptor getMappedInterceptor() {
        return new MappedInterceptor(new String[] { "/html/**" }, new MyInterceptor());
    }

}
