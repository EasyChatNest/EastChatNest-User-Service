package com.chatnest.chatnestuserservice.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.chatnest.chatnestuserservice.util.JWTUtil;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Missing or malformed token\"}");
            return false;
        }

        String token = authHeader.substring(7); // 去掉 "Bearer "

        Long userId;
        try {
            userId = jwtUtil.getUserId(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Invalid or expired token\"}");
            return false;
        }

        // 从 Redis 中验证 token 是否为当前设备的登录 token
        String redisToken = redisTemplate.opsForValue().get("login:" + userId);
        if (redisToken == null || !redisToken.equals(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"You have been logged out on another device\"}");
            return false;
        }

        // token 校验成功，放行请求
        return true;
    }
}
