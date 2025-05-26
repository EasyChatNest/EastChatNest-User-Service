package com.chatnest.chatnestuserservice.config;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 *
 * JwtInterceptor 是一个“请求拦截器”，
 * 它在请求到达你的 Controller 之前，先检查请求里有没有合法的 JWT Token，
 * 并提取出用户身份信息（比如 userId）供你后续使用。
 */
public class WebMvcConfig implements WebMvcConfigurer {

}
