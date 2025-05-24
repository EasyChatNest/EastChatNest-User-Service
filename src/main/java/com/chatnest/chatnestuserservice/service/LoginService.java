package com.chatnest.chatnestuserservice.service;

// For user login Service Logic

import com.chatnest.chatnestuserservice.dto.Request.LoginRequest;
import com.chatnest.chatnestuserservice.dto.Response.LoginResponse;
import com.chatnest.chatnestuserservice.entity.User;
import com.chatnest.chatnestuserservice.mapper.UserMapper;
import com.chatnest.chatnestuserservice.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginResponse login(LoginRequest request) {
//        User user = userMapper.findByUsername(request.getUsername());

        //


        //


        return new LoginResponse();
    }
}
