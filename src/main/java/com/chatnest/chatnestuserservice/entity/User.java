package com.chatnest.chatnestuserservice.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Long id;

    private String username;         // Id（unique）
    private String email;            // email（unique）
    private String phone;            // phone number
    private String password;         // password（using BCrypt）

    private String nickname;         // nickname
    private String avatarUrl;        // avatar URL
    private String gender;           // gender：male/female/other
    private String status;           // user status：active / frozen / deleted

    private String role;             // roles：user / admin / moderator
    private String region;           // region（such as  CN/US）

    private String signature;        // signature
    private Integer loginType;       // loginType：0= password，1= wechat，2=verification code
    private LocalDateTime lastLoginTime;

    private LocalDateTime createdAt; // create time
    private LocalDateTime updatedAt; // edit time
}
