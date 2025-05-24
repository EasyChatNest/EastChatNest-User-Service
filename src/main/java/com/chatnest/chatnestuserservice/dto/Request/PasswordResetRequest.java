package com.chatnest.chatnestuserservice.dto.Request;


import lombok.Data;

@Data
public class PasswordResetRequest {
    private String email;        // using email
    private String verifyCode;   // has been sent to the targeted email
    private String newPassword;

    private String confirmPassword;
}
