package com.chatnest.chatnestuserservice.mapper;
import com.chatnest.chatnestuserservice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;

@Mapper
public interface UserMapper {

    // find the user by username
    User findByUsername(@Param("username") String username);

    // find the user by email
    User findByEmail(@Param("email") String email);

    // Insert user
    void insertUser(User user);

    // last sign in time
    void updateLoginTime(@Param("userId") Long userId, @Param("loginTime") LocalDateTime loginTime);
}
