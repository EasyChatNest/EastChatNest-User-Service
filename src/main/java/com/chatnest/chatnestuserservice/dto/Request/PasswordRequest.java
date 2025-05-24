package com.chatnest.chatnestuserservice.dto.Request;


import lombok.Data;

/**
 *  There are two scenes
 *  1. The user has already sign in, but wanna to change the password
 *  2. The user forget the password
 *
 *
 */
@Data
public class PasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
