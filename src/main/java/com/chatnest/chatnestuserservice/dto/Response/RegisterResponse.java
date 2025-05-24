package com.chatnest.chatnestuserservice.dto.Response;

import lombok.Data;

@Data
public class RegisterResponse {
    private Long userId;
    private String username;
    private String nickname;
    private String message;
}
