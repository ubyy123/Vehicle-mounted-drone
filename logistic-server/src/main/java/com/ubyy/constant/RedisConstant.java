package com.ubyy.constant;

/**
 * FileName:    RedisConstant
 * Description: Key和缓存静态变量
 */
public interface RedisConstant {

    // Key
    String EMAIL = "EMAIL_"; // 邮箱缓存前缀
    String EMAIL_REQUEST_VERIFY = "EMAIL_REQUEST_VERIFY_"; // 邮箱请求的权限码

    // 缓存时间
    int EXPIRE_TEN_SECOND = 10; // 10s
    int EXPIRE_ONE_MINUTE = 60; // 1分钟
    int EXPIRE_FIVE_MINUTE = 5 * 60; // （五分钟）
    int EXPIRE_HALF_HOUR = 30 * 60; // 半小时（30分钟）
    int EXPIRE_ONE_DAY = 24 * 60 * 60; // （1天）
}
