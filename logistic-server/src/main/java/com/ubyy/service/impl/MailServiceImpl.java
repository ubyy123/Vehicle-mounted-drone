package com.ubyy.service.impl;

import com.ubyy.service.IMailService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
public class MailServiceImpl implements IMailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String theme, String content, String... cc) {
        // 创建邮件对象
        SimpleMailMessage message = new SimpleMailMessage();

        try {
            message.setFrom(String.valueOf(new InternetAddress(from, "yangbiao", "UTF-8")));      // 发件人
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        message.setTo(to);          // 收件人
        message.setSubject(theme);  // 标题
        message.setText(content);   // 内容

        if (ArrayUtils.isNotEmpty(cc)) {
            message.setCc(cc);
        }

        // 发送
        javaMailSender.send(message);
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content, String... cc) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        if (ArrayUtils.isNotEmpty(cc)) {
            helper.setCc(cc);
        }
        javaMailSender.send(message);
    }
}
