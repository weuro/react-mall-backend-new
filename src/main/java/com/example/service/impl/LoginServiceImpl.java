package com.example.service.impl;

import com.example.dto.ResponseResult;
import com.example.dto.UserDto;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.service.LoginService;
import com.example.util.LogUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;

    @Override
    public ResponseResult login(UserDto userDto) {
        // 如果账户或密码为空，返回错误提示
        if (StringUtils.isEmpty(userDto.getPhone()) || StringUtils.isEmpty(userDto.getPassword())) {
            LogUtil.log("账户或密码不能为空");
            return new ResponseResult(400, "账户或密码不能为空", null);
        }

        // 根据手机号查询用户
        User user = userMapper.findUserByPhone(userDto.getPhone());
        if (user == null) {
            LogUtil.log("用户未注册");
            return new ResponseResult(400, "用户未注册", null);
        }

        // 如果密码不匹配，返回错误提示
        if (!user.getPassword().equals(userDto.getPassword())) {
            LogUtil.log("密码错误");
            return new ResponseResult(400, "密码错误", null);
        }

        Map<String, String> map = new HashMap<>();
        map.put("token", user.toString());
        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public ResponseResult register(User user) {
        // 如果账户,密码或用户名为空，返回错误提示
        if (StringUtils.isEmpty(user.getPhone()) || StringUtils.isEmpty(user.getPassword()) || StringUtils.isEmpty(user.getUsername())) {
            LogUtil.log("账户,密码或用户名不能为空");
            return new ResponseResult(400, "账户,密码或用户名不能为空", null);
        }

        // 根据手机号查询用户
        User user1 = userMapper.findUserByPhone(user.getPhone());
        if (user1 != null) {
            LogUtil.log("用户已注册");
            return new ResponseResult(400, "用户已注册", null);
        }

        // 注册用户
        int res = userMapper.insertUser(user);
        if (res == 1) {
            return new ResponseResult(200, "注册成功", null);
        }

        return new ResponseResult(400, "注册失败", null);
    }
}
