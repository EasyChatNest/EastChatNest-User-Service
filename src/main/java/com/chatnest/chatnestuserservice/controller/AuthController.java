package com.chatnest.chatnestuserservice.controller;

import com.chatnest.chatnestuserservice.dto.Request.LoginRequest;
import com.chatnest.chatnestuserservice.dto.Request.RegisterRequest;
import com.chatnest.chatnestuserservice.dto.Response.LoginResponse;
import com.chatnest.chatnestuserservice.dto.Response.LogoutResponse;
import com.chatnest.chatnestuserservice.dto.Response.RegisterResponse;
import com.chatnest.chatnestuserservice.service.LoginService;
import com.chatnest.chatnestuserservice.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterService registerService;
    private final LoginService loginService;

    /**
     * User Register API
     * @param request Parameter（username, email, phone, password, nickname...）
     * @return The basic information of user (dto/response/RegisterResponse)
     */
    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return registerService.register(request);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }

    @PostMapping("/logout")
    public LogoutResponse logout(){
        // has to be changed
        return new LogoutResponse();

    }


}
