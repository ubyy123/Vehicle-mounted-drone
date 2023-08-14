package com.ubyy.controller;

import com.ubyy.pojo.Login;
import com.ubyy.pojo.RespBean;
import com.ubyy.pojo.RespMailBean;
import com.ubyy.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FileName:    CommonController
 * Description:
 */
@RestController
@RequestMapping("/common")
@CrossOrigin
public class CommonController {

    @Autowired
    private ICommonService commonService;

    // 权限码请求接口
    @PostMapping("code/request")
    public RespMailBean getRequestPermissionCode(@RequestBody String emailJson) {
        return commonService.getRequestPermissionCode(emailJson);
    }

    // 邮箱验证码接口
    @PostMapping("code/email")
    public RespMailBean sendEmailCode(@RequestBody Login loginParam) {
        return commonService.sendEmailCode(loginParam);
    }
}
