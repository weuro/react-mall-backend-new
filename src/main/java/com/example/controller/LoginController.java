package com.example.controller;

import com.example.dto.ResponseResult;
import com.example.dto.UserDto;
import com.example.model.User;
import com.example.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/api/user/login")
    public ResponseResult login(@RequestBody UserDto userDto) {
        return loginService.login(userDto);
    }

    @PostMapping("/api/user/register")
    public ResponseResult register(@RequestBody User user) {
        return loginService.register(user);
    }
}
