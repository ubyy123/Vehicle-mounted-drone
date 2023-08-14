package com.ubyy.constant;

import lombok.Data;
import lombok.Getter;


@Getter
public enum HttpStatusEnum {

    EMAIL_ERROR(4001, "邮箱格式不正确"),
    PARAM_ERROR(4002, "参数格式不正确"),
    CODE_ERROR(4002, "验证码不正确"),
    PASSWORD_ERROR(4003, "密码错误"),
    USER_NOT_EXIST(4004, "用户不存在"),
    EMAIL_ALREADY_EXIST(4005, "邮箱已被注册"),
    PASSWORD_INCONSISTENT(4006, "密码不一致"),
    PARAM_ILLEGAL(4007, "参数不合法"),

    INTERNAL_SERVER_ERROR(500, "服务器异常"),
    UNKNOWN_ERROR(66666, "未知异常, 联系管理员"),
    ILLEGAL_OPERATION(88888, "非法操作");

    private final int code;
    private final String msg;

    HttpStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}