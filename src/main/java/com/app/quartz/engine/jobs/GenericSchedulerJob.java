package com.app.quartz.engine.jobs;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.app.quartz.engine.service.JobRequestProcessService;

import lombok.extern.slf4j.Slf4j;

/**
 * one instance for one job key 
 */
@Slf4j
public class GenericSchedulerJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(BirthdayGreetingJob.class);
	
	@Autowired
	private JobRequestProcessService jobRequestProcessService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("-------------------------- Start GenericSchedulerJob " + new Date() + "-------------------------------------");

		JobKey key = context.getJobDetail().getKey();
		System.out.println("Cron Job started with key .....");
		System.out.println("Name					:" + key.getName());
		System.out.println("Group 					:" + key.getGroup());
		System.out.println("Date 					:" + new Date());
		System.out.println("Thread Name 			:" + Thread.currentThread().getName());
//		jobRequestProcessService.requestURLClient(context);

		JobDataMap dataMap = context.getMergedJobDataMap();
		String myValue = dataMap.getString("myKey");

		System.out.println("Value					:" + myValue);
		System.out.println("Thread					: " + Thread.currentThread().getName() + " stopped.");
		
		System.out.println("-------------------------- End GenericSchedulerJob " + new Date() + "-------------------------------------");
	}
}
