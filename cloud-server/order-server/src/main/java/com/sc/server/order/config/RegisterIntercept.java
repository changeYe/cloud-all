package com.sc.server.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author yuantongqin
 * desc:
 * 2020-05-24
 */
@Configuration
public class RegisterIntercept extends WebMvcConfigurationSupport {

    @Autowired
    private LoginIntercept loginIntercept;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 添加日志追踪拦截器
        registry.addInterceptor(loginIntercept).addPathPatterns("/**");
        super.addInterceptors(registry);
    }


}
