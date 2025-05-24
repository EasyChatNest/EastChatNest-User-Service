package com.chatnest.chatnestuserservice.service;
/**
 *  For sign up and userUpdate service
 *  2025-May-24
 *
 */
import com.chatnest.chatnestuserservice.dto.Request.RegisterRequest;
import com.chatnest.chatnestuserservice.dto.Response.RegisterResponse;
import com.chatnest.chatnestuserservice.entity.User;
import com.chatnest.chatnestuserservice.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public RegisterResponse register(RegisterRequest request){
        // Check if the user exits by userName
        if (userMapper.findByUsername(request.getUsername()) !=null){
            throw new RuntimeException("Username already exists.");
        }
        // Check if the user exits by Email
        if (userMapper.findByEmail(request.getEmail()) != null){
            throw new RuntimeException("Email already exists");
        }
        // After this line, means the users is completely new
        // register
        // Build a new user in entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNickname(request.getNickname());
        user.setAvatarUrl("/images/default.png");
        user.setGender(request.getGender() != null ? request.getGender() : "unknown");
        user.setRegion(request.getRegion());
        user.setSignature("Nothing ~~~");
        user.setStatus("active");
        user.setRole("user");
        user.setLoginType(0); // regular password
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        // Insert into database
        userMapper.insertUser(user);
        // Build the response
        RegisterResponse response = new RegisterResponse();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setNickname(user.getNickname());
        response.setMessage("Registered successfully.");
        return response;
    }
}
