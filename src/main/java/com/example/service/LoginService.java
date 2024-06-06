package com.example.service;

import com.example.dto.ResponseResult;
import com.example.dto.UserDto;
import com.example.model.User;

public interface LoginService {

    ResponseResult login(UserDto userDto);

    ResponseResult register(User user);
}
