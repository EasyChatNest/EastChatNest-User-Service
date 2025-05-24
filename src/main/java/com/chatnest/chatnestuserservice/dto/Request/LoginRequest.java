package com.chatnest.chatnestuserservice.dto.Request;

import lombok.Data;

@Data
public class LoginRequest {

    private String identifier; // userName or email, etc
    private String password;
    private Integer loginType; // sent by the frontend
}
