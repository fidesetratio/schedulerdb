package com.app.quartz.engine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.quartz.engine.entity.NotificationsHistory;
import com.app.quartz.engine.repository.NotificationsHistoryRepository;
import com.app.quartz.engine.service.NotificationsConfigurationService;
import com.app.quartz.engine.service.NotificationsHistoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class NotificationsHistoryServiceImpl implements NotificationsHistoryService {

	@Autowired
	private NotificationsHistoryRepository nhRepository;
	
	@Autowired
	private NotificationsConfigurationService notificationsConfigurationService;
	
	@Override
	public NotificationsHistory saveNotificationsHistory(NotificationsHistory history) {
		return nhRepository.saveAndFlush(history);
	}

	@Override
	public List<NotificationsHistory> getAllnotificationHistory() {
		List<NotificationsHistory> list = nhRepository.findAllByOrderByNhIdAsc();
		for (NotificationsHistory nh: list) {
			nh.setNhNcNotiftype(notificationsConfigurationService.getPlatformType(nh.getNhNcId()));
		}
		return list;
	}

	@Override
	public NotificationsHistory getNotificationHistoryById(long id) {
		NotificationsHistory nh = nhRepository.getOne(id);
		
		if (nh != null) {
			nh.setNhNcNotiftype(notificationsConfigurationService.getPlatformType(nh.getNhNcId()));
		}
		
		return nh;
	}	
}
