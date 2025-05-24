package com.chatnest.chatnestuserservice.service;

// For user login Service Logic
import com.chatnest.chatnestuserservice.common.BusinessException;
import com.chatnest.chatnestuserservice.dto.Request.LoginRequest;
import com.chatnest.chatnestuserservice.dto.Response.LoginResponse;
import com.chatnest.chatnestuserservice.entity.User;
import com.chatnest.chatnestuserservice.mapper.UserMapper;
import com.chatnest.chatnestuserservice.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginResponse login(LoginRequest request) {
        // check the username or email
        User user = userMapper.findByEmail(request.getIdentifier());
        if (user == null) {
            user = userMapper.findByUsername(request.getIdentifier());
        }
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        // check if the password is correct or not
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException("Invalid password");
        }

        // generate from the backend
        user.setLastLoginTime(LocalDateTime.now());
        userMapper.updateLoginTime(user.getId(), user.getLastLoginTime());

        // generate token
        String token = jwtUtil.generateToken(user.getId(), user.getUsername(), user.getRole());
        // return the LoginResponse
        LoginResponse response = new LoginResponse();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setNickname(user.getNickname());
        response.setRole(user.getRole());
        //  set the JWT Token
        response.setToken(token);
        return response;
    }
}
