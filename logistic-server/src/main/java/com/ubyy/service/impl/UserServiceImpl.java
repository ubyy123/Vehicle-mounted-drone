package com.ubyy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ubyy.constant.HttpStatusEnum;
import com.ubyy.constant.RedisConstant;
import com.ubyy.mapper.UserMapper;
import com.ubyy.pojo.Login;
import com.ubyy.pojo.RespMailBean;
import com.ubyy.pojo.User;
import com.ubyy.service.IUserService;
import com.ubyy.utils.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangbiao
 * @since 2022-10-16
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public RespMailBean login(Login loginParam) {
        if (loginParam == null) return RespMailBean.error(HttpStatusEnum.PARAM_ILLEGAL);

        // 获取参数
        String email = loginParam.getEmail();
        String password = loginParam.getPassword();

        if (StringUtils.isAnyBlank(email, password)) {
            // 非空
            return RespMailBean.error(HttpStatusEnum.PARAM_ILLEGAL);
        }else if (!StringUtil.checkEmail(email)) {
            // 邮箱格式校验
            return RespMailBean.error(HttpStatusEnum.EMAIL_ERROR);
        }else if (!StringUtil.checkPassword(password)) {
            // 密码格式
            return RespMailBean.error(HttpStatusEnum.PASSWORD_ERROR);
        }

        // 构件条件对象 select salt from user where email = #{email} limit 1
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("salt");
        wrapper.eq("email", email);
        wrapper.last("limit 1");

        // 查询结果
        User user = this.baseMapper.selectOne(wrapper);
        if (user == null) {
            // 用户不存在
            return RespMailBean.error(HttpStatusEnum.USER_NOT_EXIST);
        }

        // 获取加密盐
        String salt = user.getSalt();
        // 重新设置条件 select id from user where email = #{email} and password #{password} limit 1
        wrapper.clear();
        wrapper.select("id");
        wrapper.eq("email", email);
        wrapper.eq("password", DigestUtils.md5Hex(password + salt));
        wrapper.last("limit 1");
        // 查询用户
        user = this.baseMapper.selectOne(wrapper);

        return user == null ? RespMailBean.error(HttpStatusEnum.PASSWORD_ERROR) : RespMailBean.ok();
    }

    @Override
    public RespMailBean register(Login loginParam) {
        if (loginParam == null) return RespMailBean.error(HttpStatusEnum.PARAM_ILLEGAL);

        // 获取参数
        String email = loginParam.getEmail();
        String password = loginParam.getPassword();
        String passwordConfirm = loginParam.getPasswordConfirm();
        String code = loginParam.getCode();

        if (StringUtils.isAnyBlank(email, password, passwordConfirm, code)) {
            // 非空
            return RespMailBean.error(HttpStatusEnum.PARAM_ILLEGAL);
        }else if (!StringUtil.checkEmail(email)) {
            // 邮箱格式校验
            return RespMailBean.error(HttpStatusEnum.EMAIL_ERROR);
        }else if (!password.equals(passwordConfirm)) {
            // 密码一致校验
            return RespMailBean.error(HttpStatusEnum.PASSWORD_INCONSISTENT);
        }else if (!StringUtil.checkPassword(password) || code.length() != 6) {
            // 密码格式和验证码长度校验
            return RespMailBean.error(HttpStatusEnum.PARAM_ILLEGAL);
        }

        // 构造查询条件对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id");
        wrapper.eq("email", email);
        wrapper.last("limit 1");

        // 查询用户，是否存在
        if (this.baseMapper.selectOne(wrapper) != null) {
            return RespMailBean.error(HttpStatusEnum.EMAIL_ALREADY_EXIST);
        }

        // 获取正确的验证码
        String rightCode = redisTemplate.opsForValue().get(RedisConstant.EMAIL + email);
        if (!code.equals(rightCode)) {
            // 验证码比对
            return RespMailBean.error(HttpStatusEnum.CODE_ERROR);
        }

        // 删除验证码
        redisTemplate.delete(RedisConstant.EMAIL + email);

        // 注册用户
        User user = new User();
        // 获取加密盐
        String salt = StringUtil.randomEncryptedSalt();
        // 邮箱
        user.setEmail(email);
        // 密码加密（原明文密码 + 随机加密盐） md5加密
        user.setPassword(DigestUtils.md5Hex(password + salt));
        // 加密盐
        user.setSalt(salt);

        // 插入数据
        return this.baseMapper.insert(user) == 0 ? RespMailBean.error(HttpStatusEnum.UNKNOWN_ERROR) : RespMailBean.ok();

    }

    @Override
    public RespMailBean findPassword(Login loginParam) {
        if (loginParam == null) return RespMailBean.error(HttpStatusEnum.PARAM_ILLEGAL);

        // 获取参数
        String email = loginParam.getEmail();
        String password = loginParam.getPassword();
        String code = loginParam.getCode();

        if (StringUtils.isAnyBlank(email, password, code)) {
            // 非空
            return RespMailBean.error(HttpStatusEnum.PARAM_ILLEGAL);
        }else if (!StringUtil.checkEmail(email)) {
            // 邮箱格式校验
            return RespMailBean.error(HttpStatusEnum.EMAIL_ERROR);
        }else if (!StringUtil.checkPassword(password) || code.length() != 6) {
            // 密码格式和验证码长度校验
            return RespMailBean.error(HttpStatusEnum.PARAM_ILLEGAL);
        }

        // 构造查询条件对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "salt");
        wrapper.eq("email", email);
        wrapper.last("limit 1");

        // 查询用户，是否存在
        User user = this.baseMapper.selectOne(wrapper);
        if (user == null) {
            return RespMailBean.error(HttpStatusEnum.USER_NOT_EXIST);
        }

        // 获取正确的验证码
        String rightCode = redisTemplate.opsForValue().get(RedisConstant.EMAIL + email);
        if (!code.equals(rightCode)) {
            // 验证码比对
            return RespMailBean.error(HttpStatusEnum.CODE_ERROR);
        }

        // 删除验证码
        redisTemplate.delete(RedisConstant.EMAIL + email);

        // 修改密码
        User user1 = new User();
        user1.setId(user.getId());
        user1.setPassword(DigestUtils.md5Hex(password + user.getSalt()));

        // 修改
        return this.baseMapper.updateById(user1) == 0 ? RespMailBean.error(HttpStatusEnum.UNKNOWN_ERROR) : RespMailBean.ok();
    }
}
