package com.chatnest.chatnestuserservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *  Aimed to check if the validation code sent to the targeted email is valid or not
 *
 *
 */
@Service
public class SendVerifyCodeService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // send a code to the targeted email

    public String sendCode(String email) {
        return "hello";
    }


    // save the code in redis
    //    redisTemplate.opsForValue().set(key, code, 5,TimeUnit.MINUTES);


    // compare if the code is same or not

    public boolean verifyCode(String email, String inputCode) {


        return true;
    }

    private String generateCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
