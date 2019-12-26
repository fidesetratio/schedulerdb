package com.app.quartz.engine.service;

import java.util.List;

import com.app.quartz.engine.entity.NotificationsConfiguration;

public interface NotificationsConfigurationService {

	public NotificationsConfiguration getConfigByPlatform(int platform);
	public List<NotificationsConfiguration> getAllNotifications();
	public NotificationsConfiguration getConfigById(long id);
	public String getPlatformType(long id);
}
