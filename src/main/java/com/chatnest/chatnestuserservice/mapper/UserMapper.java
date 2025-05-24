package com.chatnest.chatnestuserservice.mapper;
import com.chatnest.chatnestuserservice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {

    // 根据用户名查找
    User findByUsername(@Param("username") String username);

    // 根据邮箱查找
    User findByEmail(@Param("email") String email);

    // 插入新用户（注册）
    void insertUser(User user);

    // 更新最后登录时间（登录时使用，可选）
    void updateLoginTime(@Param("userId") Long userId, @Param("loginTime") LocalDateTime loginTime);
}
