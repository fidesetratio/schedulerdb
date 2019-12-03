package com.app.quartz.engine.jobs;

import java.util.Date;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
@DisallowConcurrentExecution
public class RestScheduleJob extends QuartzJobBean {
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("-------------------------- Start RestScheduleJob -------------------------------------");
		System.out.println(new Date().toString());
		
		JobDataMap map = context.getMergedJobDataMap();
		String url = map.getString("url");
		System.out.println(url);

		if (map.get("params") instanceof Map) {
			url = map.getString("url");
			System.out.println(url);
		}

		System.out.println("-------------------------- End RestScheduleJob -------------------------------------");
	}

}
