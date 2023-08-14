package com.ubyy.service;

import com.ubyy.pojo.Login;
import com.ubyy.pojo.RespBean;
import com.ubyy.pojo.RespMailBean;

/**
 * FileName:    CommonService
 * Description:
 */
public interface ICommonService {

    /**
     * 获取请求权限码
     * @param emailJson 邮箱
     * @return
     */
    RespMailBean getRequestPermissionCode(String emailJson);

    /**
     * 发送邮箱验证码
     * @param loginParam （邮箱和权限码）
     * @return
     */
    RespMailBean sendEmailCode(Login loginParam);
}
