package com.app.quartz.engine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.quartz.engine.entity.SchedulerJobHistory;
import com.app.quartz.engine.repository.SchedulerJobHistoryRepository;
import com.app.quartz.engine.service.SchedulerJobHistoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class SchedulerJobHistoryServiceImpl implements SchedulerJobHistoryService {

	@Autowired
	private SchedulerJobHistoryRepository schedulerJobHistoryRepository;
	
	@Override
	public List<SchedulerJobHistory> getAllHistory() {
		return schedulerJobHistoryRepository.findAllByOrderBySjhFireTimeDesc();
	}

}
