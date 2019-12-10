package com.app.quartz.engine.jobs;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.service.SchedulerJobService;
import com.app.quartz.engine.util.RestClient;

@Slf4j
@DisallowConcurrentExecution
public class RestCronJob extends QuartzJobBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestCronJob.class);

	@Autowired
	private SchedulerJobService schedulerJobService;

	@Autowired
	private RestClient restClient;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap map = context.getMergedJobDataMap();
		System.out.println("-------------------------- Start RestCronJob -------------------------------------");
		System.out.println(new Date().toString());
		
		List<SchedulerJobInfo> jobsList = schedulerJobService.schedulerJobList();

		for (SchedulerJobInfo j : jobsList) {
			if (j.getUrl() != null) {
				try {
					restClient.restClientOutput(j.getUrl());
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		System.out.println();
		System.out.println("-------------------------- End RestCronJob -------------------------------------");

	}

}
