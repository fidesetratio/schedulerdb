package com.app.quartz.engine.service;

import java.util.List;

import com.app.quartz.engine.entity.NotificationsHistory;

public interface NotificationsHistoryService {

	public NotificationsHistory saveNotificationsHistory(NotificationsHistory history);
	public List<NotificationsHistory> getAllnotificationHistory();
}
