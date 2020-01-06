package com.app.quartz.engine.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quartz.engine.dto.AjaxRequestModel;
import com.app.quartz.engine.dto.SchedulerJob;
import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.obj.ProcessType;
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
		List<SchedulerJobInfo> schedulerInfolist = ajaxRequestModel.getJobList();
		if (ajaxRequestModel.getJobProcess().trim().equalsIgnoreCase(ProcessType.valueOf("PAUSE").getValue())) {
			for (SchedulerJobInfo sc : schedulerInfolist) {
				schedulerJobService.pauseScheduleJob(sc);
				logger.debug("Job: " + sc.getJobName() + " is paused. Time: " + new Date());
			}
		} else if (ajaxRequestModel.getJobProcess().trim().equalsIgnoreCase(ProcessType.valueOf("RESUME").getValue())) {
			for (SchedulerJobInfo sc : schedulerInfolist) {
				schedulerJobService.resumeScheduleJob(sc);
				logger.debug("Job: " + sc.getJobName() + " is resumed. Time: " + new Date());
			}
		} else if (ajaxRequestModel.getJobProcess().trim().equalsIgnoreCase(ProcessType.valueOf("RESUME_ALL").getValue())) {
			schedulerJobService.resumeAllSchedulers();
			logger.debug("All job is resumed. Time: " + new Date());
		} else if (ajaxRequestModel.getJobProcess().trim().equalsIgnoreCase(ProcessType.valueOf("PAUSE_ALL").getValue())) {
			schedulerJobService.pauseAllSchedulers();
			logger.debug("All job is paused. Time: " + new Date());
		} else if (ajaxRequestModel.getJobProcess().trim().equalsIgnoreCase(ProcessType.valueOf("DELETE").getValue())) {
			schedulerJobService.deleteScheduleJob(schedulerInfolist);
		}
	}

	@Override
	public List<SchedulerJob> searchScheduler(String jobSearchoption, String jobSearchinput) {
		List<SchedulerJob> schedulerJoblist = new ArrayList<SchedulerJob>();
		String name = "";
		String group = "";
		if (jobSearchoption.toUpperCase().equals("BOTHJOBNAMEGROUP")) {
			name = jobSearchinput;
			group = jobSearchinput;
		} else if (jobSearchoption.toUpperCase().equals("INJOBNAME")) {
			name = jobSearchinput;
		} else if (jobSearchoption.toUpperCase().equals("INJOBGROUP")) {
			group = jobSearchinput;
		}
		
		schedulerJoblist = schedulerJobService.searchSchedulerb(name, group);
		
		return schedulerJoblist;
	}
}
