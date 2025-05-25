package com.chatnest.chatnestuserservice.service;

import com.chatnest.chatnestuserservice.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SendVerifyCodeService {

    @Autowired
    private RedisUtils redisUtils;

    private static final String PREFIX = "verify:";

    /**
     * 发送验证码：生成验证码并保存到 Redis
     */
    public void sendCode(String email) {
        String code = generateCode();
        String key = PREFIX + email;

        // save it into Redis，lasting 5 mins
        redisUtils.set(key, code, 5);

        // 此处你可以加上发送邮件逻辑，比如用 MailService.send(email, code)
        System.out.println("Email has been sent to user");

    }

    /**
     * 校验验证码是否正确
     */
    public boolean verifyCode(String email, String inputCode) {
        String key = PREFIX + email;
        String realCode = redisUtils.get(key);

        // 验证码匹配
        if (realCode != null && realCode.equals(inputCode)) {
            redisUtils.delete(key); // 验证成功后删除
            return true;
        }

        return false;
    }

    /**
     *
     * Generate a random code
     */
    private String generateCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
