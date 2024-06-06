package com.example;

import com.example.mapper.UserMapper;
import com.example.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void insertUser() {
        System.out.println(userMapper.insertUser(
                new User()
                        .setUsername("test")
                        .setPassword("123456")
                        .setPhone("12345678901")
                )
        );
    }
}
