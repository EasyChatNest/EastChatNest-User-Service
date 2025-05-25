package com.chatnest.chatnestuserservice.controller;

import com.chatnest.chatnestuserservice.dto.Request.CodeRequest;
import com.chatnest.chatnestuserservice.service.SendVerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verify")
public class VerifyController {

    @Autowired
    private SendVerifyCodeService verifyService;
    /**
     *
     *
     * Send code to user's email
     * @param email
     * @return
     */
    @PostMapping("/send-code")
    public ResponseEntity<?> sendCode(@RequestParam String email) {
        verifyService.sendCode(email);
        return ResponseEntity.ok(" code has been sent ");
    }

    @PostMapping("/check-code")
    public ResponseEntity<?> checkCode(@RequestBody CodeRequest request) {
        boolean result = verifyService.verifyCode(request.getEmail(), request.getCode());

        if (result) {
            return ResponseEntity.ok("Successful ");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong ");
        }
    }
}
