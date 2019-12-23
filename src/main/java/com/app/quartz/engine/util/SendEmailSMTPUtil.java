package com.app.quartz.engine.util;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.app.quartz.engine.entity.NotificationsConfiguration;
import com.app.quartz.engine.entity.NotificationsHistory;
import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.service.NotificationsConfigurationService;
import com.app.quartz.engine.service.NotificationsHistoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SendEmailSMTPUtil {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SendEmailSMTPUtil.class);
	
	@Autowired
	private NotificationsConfigurationService ncService;
	
	@Autowired
	private NotificationsHistoryService nhService;
	
	private String process;
	private SchedulerJobInfo schedulerJobInfo;

	@Async
	public void sendMail(int platform, String process, SchedulerJobInfo schedulerJobInfo) {
		this.process = process;
		this.schedulerJobInfo = schedulerJobInfo;
		
		// ambil data konfigurasi dari tabel notification configuration
		NotificationsConfiguration nc = ncService.getConfigByPlatform(platform);
		
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", nc.getNcHost());
        prop.put("mail.smtp.port", nc.getNcPort());
        prop.put("mail.smtp.ssl.trust", nc.getNcHost());

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(nc.getNcUsername(), nc.getNcPassword());
            }
        });

        try {
        	Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(nc.getNcSender()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(nc.getNcReceiver()));
            
            String subject = replaceFieldInSubjectAndContent(nc.getNcSubject());
            message.setSubject(subject);

            String content = replaceFieldInSubjectAndContent(nc.getNcContent());

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(content, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);
            
            // kirim email
            Transport.send(message);
            
            System.out.println("Generate notification history");
            // simpan history email
            NotificationsHistory nh = new NotificationsHistory(nc, subject, content);
    		nhService.saveNotificationsHistory(nh);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SendEmailSMTPUtil:sendMail.");
        }
    }
	
	private String replaceFieldInSubjectAndContent(String str) {
		str = str.replace("{Process}", this.process);
		if (this.schedulerJobInfo != null) {
			str = str.replace("{JobName}", this.schedulerJobInfo.getJobName());
			str = str.replace("{GroupName}", this.schedulerJobInfo.getJobGroup());
		}
		return str;
	}
	
}
