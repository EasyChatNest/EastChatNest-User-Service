package com.chatnest.chatnestuserservice.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationMs;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * 生成 Token
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
     * 验证 Token 是否合法（签名 + 是否过期）
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
     * 判断 token 是否过期
     */
    public boolean isTokenExpired(String token) {
        try {
            Date expiration = getClaimsFromToken(token).getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true; // 无法解析，直接视为过期
        }
    }

    /**
     * 从 Token 获取 Claims
     */
    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody();
    }

    /**
     * 安全获取 Claims，防止异常崩溃
     */
    public Claims safeGetClaims(String token) {
        try {
            return getClaimsFromToken(token);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取 UserId
     */
    public Long getUserId(String token) {
        Claims claims = safeGetClaims(token);
        return claims != null ? claims.get("userId", Long.class) : null;
    }

    /**
     * 获取用户名
     */
    public String getUsername(String token) {
        Claims claims = safeGetClaims(token);
        return claims != null ? claims.getSubject() : null;
    }

    /**
     * 获取角色
     */
    public String getRole(String token) {
        Claims claims = safeGetClaims(token);
        return claims != null ? claims.get("role", String.class) : null;
    }

    /**
     * 可选：刷新 token（旧 token 合法，但快过期时调用）
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
