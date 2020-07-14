package com.sc.hystrix.custom.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yuantongqin
 * description: 将拦截器注册如web请求容器中
 * 2020/5/10
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TimeoutIntercept timeoutIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeoutIntercept);
    }
}
