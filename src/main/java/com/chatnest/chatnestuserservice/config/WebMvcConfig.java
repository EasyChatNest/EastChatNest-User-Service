package com.chatnest.chatnestuserservice.config;

import com.chatnest.chatnestuserservice.config.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/user/**") // 需要拦截的路径
                .excludePathPatterns(
                        "/api/user/login",
                        "/api/user/register",
                        "/api/user/sendCode"
                ); // 不需要拦截的路径
    }
}
