package com.app.quartz.engine.service;

import com.app.quartz.engine.entity.NotificationsConfiguration;

public interface NotificationsConfigurationService {

	public NotificationsConfiguration getConfigByPlatform(int platform);
}
