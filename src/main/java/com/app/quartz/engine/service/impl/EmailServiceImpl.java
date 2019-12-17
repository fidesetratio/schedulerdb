package com.app.quartz.engine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.quartz.engine.service.EmailService;
import com.app.quartz.engine.service.NotificationsHistoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private NotificationsHistoryService notificationsHistoryService;
	
	public void sendEmail() {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("");
        message.setSubject(generateSubjectText());
        message.setText(generateContentText());

        javaMailSender.send(message);
    }
	
	private String generateContentText() {
		String content = "";
		
		return content;
	}
	
	private String generateSubjectText() {
		String content = "";
		
		return content;
	}
}
