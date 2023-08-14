package com.ubyy.service.impl;

import com.ubyy.pojo.User;
import com.ubyy.mapper.UserMapper;
import com.ubyy.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangbiao
 * @since 2022-10-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
