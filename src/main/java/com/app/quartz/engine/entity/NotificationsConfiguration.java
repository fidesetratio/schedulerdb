package com.app.quartz.engine.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notifications_configuration")
public class NotificationsConfiguration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ncId;
	private int ncPlatform;
	private String ncHost;
	private String ncPort;
	private String ncUsername;
	private String ncPassword;
	private String ncAuth;
	private String ncSender;
	private String ncReceiver;
	private String ncSubject;
	private String ncContent;
	
	public long getNcId() {
		return ncId;
	}
	public void setNcId(long ncId) {
		this.ncId = ncId;
	}
	public int getNcPlatform() {
		return ncPlatform;
	}
	public void setNcPlatform(int ncPlatform) {
		this.ncPlatform = ncPlatform;
	}
	public String getNcHost() {
		return ncHost;
	}
	public void setNcHost(String ncHost) {
		this.ncHost = ncHost;
	}
	public String getNcPort() {
		return ncPort;
	}
	public void setNcPort(String ncPort) {
		this.ncPort = ncPort;
	}
	public String getNcUsername() {
		return ncUsername;
	}
	public void setNcUsername(String ncUsername) {
		this.ncUsername = ncUsername;
	}
	public String getNcPassword() {
		return ncPassword;
	}
	public void setNcPassword(String ncPassword) {
		this.ncPassword = ncPassword;
	}
	public String getNcAuth() {
		return ncAuth;
	}
	public void setNcAuth(String ncAuth) {
		this.ncAuth = ncAuth;
	}
	public String getNcSender() {
		return ncSender;
	}
	public void setNcSender(String ncSender) {
		this.ncSender = ncSender;
	}
	public String getNcReceiver() {
		return ncReceiver;
	}
	public void setNcReceiver(String ncReceiver) {
		this.ncReceiver = ncReceiver;
	}
	public String getNcSubject() {
		return ncSubject;
	}
	public void setNcSubject(String ncSubject) {
		this.ncSubject = ncSubject;
	}
	public String getNcContent() {
		return ncContent;
	}
	public void setNcContent(String ncContent) {
		this.ncContent = ncContent;
	}
	
	@Override
	public String toString() {
		return "NotificationsConfiguration [ncId=" + ncId + ", ncPlatform=" + ncPlatform + ", ncHost=" + ncHost
				+ ", ncPort=" + ncPort + ", ncUsername=" + ncUsername + ", ncPassword=" + ncPassword + ", ncAuth="
				+ ncAuth + ", ncSender=" + ncSender + ", ncReceiver=" + ncReceiver + ", ncSubject=" + ncSubject
				+ ", ncContent=" + ncContent + "]";
	}

}
