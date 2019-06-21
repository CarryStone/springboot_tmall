package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.interceptor.LoginInterceptor;
import com.example.demo.interceptor.OtherInterceptor;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

	@Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }
	@Bean
    public OtherInterceptor getOtherIntercepter() {
        return new OtherInterceptor();
    }
	
	@Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getLoginIntercepter())
        .addPathPatterns("/**");
        registry.addInterceptor(getOtherIntercepter())
        .addPathPatterns("/**");
    }
}
