package com.chatnest.chatnestuserservice.util;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    // 建议从配置文件注入
    private final String secretKey = "my_super_secret_key_1234567890abcdef"; // 至少 32 位
    private final long expirationMs = 86400000; // 1 天

    private final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    /**
     * 生成 token
     */
    public String generateToken(Long userId, String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 验证 token 是否有效
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 从 token 获取 Claims（包含 userId、role 等）
     */
    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public Long getUserId(String token) {
        return getClaimsFromToken(token).get("userId", Long.class);
    }

    public String getUsername(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public String getRole(String token) {
        return getClaimsFromToken(token).get("role", String.class);
    }
}
