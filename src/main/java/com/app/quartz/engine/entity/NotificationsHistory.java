package com.app.quartz.engine.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notifications_history")
public class NotificationsHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long nhId;
	
	private String platform;
	
	private String sender;
	
	private String receiver;
	
	private String subject ;
	
	private String content;
	
	private boolean status;
	
	private Date sendDate;
	
	private String proccess;

	public long getNhId() {
		return nhId;
	}

	public void setNhId(long nhId) {
		this.nhId = nhId;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getProccess() {
		return proccess;
	}

	public void setProccess(String proccess) {
		this.proccess = proccess;
	}

	@Override
	public String toString() {
		return "NotificationsHistory [nhId=" + nhId + ", platform=" + platform + ", sender=" + sender + ", receiver="
				+ receiver + ", subject=" + subject + ", content=" + content + ", status=" + status + ", sendDate="
				+ sendDate + ", proccess=" + proccess + "]";
	}
}
