package com.app.quartz.engine.service;

import java.util.List;

import com.app.quartz.engine.entity.SchedulerJobHistory;

public interface SchedulerJobHistoryService {

	public List<SchedulerJobHistory> getAllHistory();
	public SchedulerJobHistory getHistoryDetail(long id);
}
