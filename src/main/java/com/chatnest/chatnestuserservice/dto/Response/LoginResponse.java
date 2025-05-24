package com.chatnest.chatnestuserservice.dto.Response;

import lombok.Data;

@Data
public class LoginResponse {
    private Long userId;
    private String username;
    private String nickname;
    private String role; // user / admin
    private String token; // JWT
}
