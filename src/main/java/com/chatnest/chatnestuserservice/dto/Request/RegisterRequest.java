package com.chatnest.chatnestuserservice.dto.Request;

import lombok.Data;

@Data
public class RegisterRequest {
        private String username;
        private String email;
        private String phone;
        private String password;
        private String nickname;
        private String gender;
        private String region;
}
