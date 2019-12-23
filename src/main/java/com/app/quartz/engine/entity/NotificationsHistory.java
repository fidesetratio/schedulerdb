package com.app.quartz.engine.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Notification history
 *
 */
@Entity
@Table(name = "notifications_history")
public class NotificationsHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long nhId;
	private long nhNcId;
	private String nhSender;
	private String nhReceiver;
	private String nhSubject ;
	private String nhContent;
	private boolean nhStatus;
	private Date nhSendDate;
	
	// constructor email sent
	public NotificationsHistory(NotificationsConfiguration nConfig, String subject, String content) {
		this.nhNcId = nConfig.getNcId();
		this.nhSender = nConfig.getNcSender();
		this.nhReceiver = nConfig.getNcReceiver();
		this.nhSubject = subject;
		this.nhContent = content;
		this.nhStatus = true;
		this.nhSendDate = new Date();
	}

	public long getNhId() {
		return nhId;
	}

	public void setNhId(long nhId) {
		this.nhId = nhId;
	}

	public long getNhNcId() {
		return nhNcId;
	}

	public void setNhNcId(long nhNcId) {
		this.nhNcId = nhNcId;
	}

	public String getNhSender() {
		return nhSender;
	}

	public void setNhSender(String nhSender) {
		this.nhSender = nhSender;
	}

	public String getNhReceiver() {
		return nhReceiver;
	}

	public void setNhReceiver(String nhReceiver) {
		this.nhReceiver = nhReceiver;
	}

	public String getNhSubject() {
		return nhSubject;
	}

	public void setNhSubject(String nhSubject) {
		this.nhSubject = nhSubject;
	}

	public String getNhContent() {
		return nhContent;
	}

	public void setNhContent(String nhContent) {
		this.nhContent = nhContent;
	}

	public boolean isNhStatus() {
		return nhStatus;
	}

	public void setNhStatus(boolean nhStatus) {
		this.nhStatus = nhStatus;
	}

	public Date getNhSendDate() {
		return nhSendDate;
	}

	public void setNhSendDate(Date nhSendDate) {
		this.nhSendDate = nhSendDate;
	}

	@Override
	public String toString() {
		return "NotificationsHistory [nhId=" + nhId + ", nhNcId=" + nhNcId + ", nhSender=" + nhSender + ", nhReceiver="
				+ nhReceiver + ", nhSubject=" + nhSubject + ", nhContent=" + nhContent + ", nhStatus=" + nhStatus
				+ ", nhSendDate=" + nhSendDate + "]";
	}
}
