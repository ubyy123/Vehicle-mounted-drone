package com.ubyy.service.impl;

import com.ubyy.service.IMailService;
import com.ubyy.service.IThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadServiceImpl implements IThreadService {

    @Autowired
    private IMailService mailService;

    @Async("taskExecutor")
    @Override
    public void sendSimpleMail(String to, String theme, String content) {
        mailService.sendSimpleMail(to, theme, content);
    }
}
