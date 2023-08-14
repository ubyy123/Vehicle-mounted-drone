package com.ubyy.service;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ubyy.pojo.Login;
import com.ubyy.pojo.RespBean;
import com.ubyy.pojo.RespMailBean;
import com.ubyy.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangbiao
 * @since 2022-10-16
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     * @param loginParam (邮箱和密码)
     * @return
     */
    RespMailBean login(Login loginParam);

    /**
     * 注册
     * @param loginParam (邮箱、密码、确认密码、验证码)
     * @return
     */
    RespMailBean register(Login loginParam);

    /**
     * 找回密码
     * @param loginParam (邮箱、密码、验证码)
     * @return
     */
    RespMailBean findPassword(Login loginParam);
}
