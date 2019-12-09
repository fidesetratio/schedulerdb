package com.app.quartz.engine.service.impl;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.app.quartz.engine.dto.AjaxRequestModel;
import com.app.quartz.engine.dto.SchedulerJob;
import com.app.quartz.engine.service.JobRequestProcessService;
import com.app.quartz.engine.service.SchedulerJobService;
import com.app.quartz.engine.util.RestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JobRequestProcessServiceImpl implements JobRequestProcessService {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerJobServiceImpl.class);
	
	@Autowired
	private SchedulerJobService schedulerJobService;
	
	@Autowired
	private RestClient restClient;
	
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
		} else if (ajaxRequestModel.getJobProcess().toUpperCase().equals("RESUME ALL")) {
			schedulerJobService.resumeAllSchedulers();
			logger.debug("All job is resumed. Time: " + new Date());
		} else if (ajaxRequestModel.getJobProcess().toUpperCase().equals("PAUSE ALL")) {
			schedulerJobService.pauseAllSchedulers();
			logger.debug("All job is paused. Time: " + new Date());
		} else if (ajaxRequestModel.getJobProcess().toUpperCase().equals("DELETE")) {
			List<JobKey> jobKeylist = new ArrayList<JobKey>();
			for (SchedulerJob sc : schedulerInfolist) {
				JobKey jobKey = new JobKey(sc.getJobName(), sc.getGroupName());
				jobKeylist.add(jobKey);
				logger.debug("Job: " + sc.getJobName() + " is deleted. Time: " + new Date());
			}
			schedulerJobService.deleteScheduleJob(jobKeylist);
		}
	}

	@Override
	public void requestURLClient(JobExecutionContext context) {
		JobDataMap map = context.getMergedJobDataMap();
		String url = map.getString("url");
		String httpMethod = map.getString("httpMethod");
		
		try {
			String response = restClient.restClientOutput(url, HttpMethod.valueOf(httpMethod.toUpperCase()), null);
			System.out.println("response : " + response);
		} catch (URISyntaxException e) {
			logger.debug("BirthdayGreetingJob.executeInternal");
			e.printStackTrace();
		}
	}
}
