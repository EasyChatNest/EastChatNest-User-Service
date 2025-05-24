package com.chatnest.chatnestuserservice.service;

import com.chatnest.chatnestuserservice.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *  responsible for logout
 *  delete / put the Token at the blacklist
 *  So, user with the Token would be able to use the service
 */
@Service
@RequiredArgsConstructor
public class LogoutService {

    private final JWTUtil jwtUtil;



}
