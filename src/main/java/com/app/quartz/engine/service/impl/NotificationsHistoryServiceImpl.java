package com.app.quartz.engine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.quartz.engine.entity.NotificationsHistory;
import com.app.quartz.engine.repository.NotificationsHistoryRepository;
import com.app.quartz.engine.service.NotificationsHistoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class NotificationsHistoryServiceImpl implements NotificationsHistoryService {

	@Autowired
	private NotificationsHistoryRepository nhRepository;
	
	@Override
	public NotificationsHistory saveNotificationsHistory(NotificationsHistory history) {
		return nhRepository.saveAndFlush(history);
	}

	
}
