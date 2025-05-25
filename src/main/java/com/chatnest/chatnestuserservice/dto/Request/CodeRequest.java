package com.chatnest.chatnestuserservice.dto.Request;


import lombok.Data;

@Data
public class CodeRequest {
    private String email;
    private String code;
}
