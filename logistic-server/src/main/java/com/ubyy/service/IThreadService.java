package com.ubyy.service;

public interface IThreadService {

    /**
     * 发送邮箱
     * @param to 收件人
     * @param theme 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String theme, String content);
}
