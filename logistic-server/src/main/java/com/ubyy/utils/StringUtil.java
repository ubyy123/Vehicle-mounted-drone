package com.ubyy.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileName:    StringUtil
 * Date:        2022/5/26 12:40
 * Description:
 */
public class StringUtil {

    /**
     * 邮箱校验
     *
     * @param email 邮箱
     * @return true or false
     */
    public static boolean checkEmail(String email) {
        String check = "^([a-zA-Z]|[0-9])(\\w|\\-)+@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    /**
     * 密码校验（长度 6-18，至少包含1个字母）
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        String check = "(?=.*[a-zA-Z])[a-zA-Z0-9]{6,18}";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(password);
        return matcher.matches();
    }

    /**
     * 随机生成六位数字验证码
     */
    public static String randomSixCode() {
        return String.valueOf(new Random().nextInt(899999) + 100000);
    }

    /**
     * 随机生成加密盐（4位随机字母 + 4位固定特殊字符）
     */
    public static String randomEncryptedSalt() {
        return RandomStringUtils.randomAlphabetic(4) + "#!$@";
    }
}
