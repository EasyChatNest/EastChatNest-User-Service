package com.chatnest.chatnestuserservice.service;

import com.chatnest.chatnestuserservice.common.BusinessException;
import com.chatnest.chatnestuserservice.dto.Request.PasswordResetRequest;
import com.chatnest.chatnestuserservice.entity.User;
import com.chatnest.chatnestuserservice.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 *  Response for finding the password
 *
 */
@Service
@RequiredArgsConstructor
public class PasswordService {

    private final UserMapper userMapper;

    public void resetPassword(PasswordResetRequest request){

        User user = userMapper.findByEmail(request.getEmail());
        // check if the email is valid or not
        if (user == null){
            throw new BusinessException("User not found");
        }
        // after this line, means this email was regiested before
        // let's change the password

        // check if the verification code is correct
        // redis




        // check if passwords are same

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BusinessException("Passwords do not match");
        }

        // store the new password into the database

//        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
//        userMapper.updatePasswordByEmail(request.getEmail(), encodedPassword);

    }


}
