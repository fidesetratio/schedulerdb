package com.app.quartz.engine.service.impl;

import java.util.Date;
import java.util.List;

import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quartz.engine.dto.AjaxRequestModel;
import com.app.quartz.engine.dto.SchedulerJob;
import com.app.quartz.engine.service.JobRequestProcessService;
import com.app.quartz.engine.service.SchedulerJobService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JobRequestProcessServiceImpl implements JobRequestProcessService {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerJobServiceImpl.class);
	
	@Autowired
	private SchedulerJobService schedulerJobService;
	
	@Override
	public void jobRequestProcess(AjaxRequestModel ajaxRequestModel) {
		List<SchedulerJob> schedulerInfolist = ajaxRequestModel.getJobList();
		if (ajaxRequestModel.getJobProcess().toUpperCase().equals("PAUSE")) {
			for (SchedulerJob sc : schedulerInfolist) {
				JobKey jobKey = new JobKey(sc.getJobName(), sc.getGroupName());
				schedulerJobService.pauseScheduleJob(jobKey);
				logger.debug("Job: " + sc.getJobName() + " is paused. Time: " + new Date());
			}
		} else if (ajaxRequestModel.getJobProcess().toUpperCase().equals("RESUME")) {
			for (SchedulerJob sc : schedulerInfolist) {
				JobKey jobKey = new JobKey(sc.getJobName(), sc.getGroupName());
				schedulerJobService.resumeScheduleJob(jobKey);
				logger.debug("Job: " + sc.getJobName() + " is resumed. Time: " + new Date());
			}
		}
	}

}
