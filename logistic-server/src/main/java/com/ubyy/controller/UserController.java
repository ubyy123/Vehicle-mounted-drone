package com.ubyy.controller;

import com.ubyy.pojo.Login;
import com.ubyy.pojo.RespMailBean;
import com.ubyy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FileName:    UserController
 * Description:
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    // 登录
    @PostMapping("login")
    public RespMailBean login(@RequestBody Login loginParam) {
        return userService.login(loginParam);
    }

    // 注册
    @PostMapping("register")
    public RespMailBean register(@RequestBody Login loginParam) {
        return userService.register(loginParam);
    }

    // 找回密码
    @PostMapping("findPassword")
    public RespMailBean findPassword(@RequestBody Login loginParam) {
        return userService.findPassword(loginParam);
    }
}
