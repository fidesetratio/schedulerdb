package com.app.quartz.engine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.quartz.engine.entity.NotificationsConfiguration;
import com.app.quartz.engine.obj.NotificationType;
import com.app.quartz.engine.repository.NotificationsConfigurationRepository;
import com.app.quartz.engine.service.NotificationsConfigurationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class NotificationsConfigurationServiceImpl implements NotificationsConfigurationService {

	@Autowired
	private NotificationsConfigurationRepository ncRepository;
	
	@Override
	public NotificationsConfiguration getConfigByPlatform(int platform) {
		NotificationsConfiguration nc = null;
		List<NotificationsConfiguration> list = ncRepository.findByPlatform(platform);
		if (list != null && list.size() > 0) {
			nc = list.get(0);
		}
		
		return nc;
	}

	@Override
	public List<NotificationsConfiguration> getAllNotifications() {
		List<NotificationsConfiguration> list = ncRepository.findAllByOrderByNcIdAsc();
		for (NotificationsConfiguration nc: list) {
			nc.setPlatformType(NotificationType.getValue(nc.getNcPlatform()));
		}
		return list;
	}

	@Override
	public NotificationsConfiguration getConfigById(long id) {
		return ncRepository.getOne(id);
	}

	@Override
	public String getPlatformType(long id) {
		NotificationsConfiguration nc = ncRepository.getOne(id);
		return NotificationType.getValue(nc.getNcPlatform());
	}

}
