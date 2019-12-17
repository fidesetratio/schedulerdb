package com.app.quartz.engine.jobs;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.app.quartz.engine.service.GenericMethodService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericSchedulerJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(GenericSchedulerJob.class);
	
	@Autowired
	private GenericMethodService genericMethodService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("-------------------------- Start GenericSchedulerJob " + new Date() + "-------------------------------------");

		JobKey key = context.getJobDetail().getKey();
		logger.debug("Cron Job started with key .....");
		logger.debug("Name: " + key.getName());
		logger.debug("Group: " + key.getGroup());
		logger.debug("Date: " + new Date());
		logger.debug("Thread Name: " + Thread.currentThread().getName());
		
		System.out.println("Cron Job started with key .....");
		System.out.println("Name					:" + key.getName());
		System.out.println("Group 					:" + key.getGroup());
		System.out.println("Date 					:" + new Date());
		System.out.println("Thread Name 			:" + Thread.currentThread().getName());
		genericMethodService.requestURLClient(context);

		System.out.println("Thread					: " + Thread.currentThread().getName() + " stopped.");
		
		System.out.println("-------------------------- End GenericSchedulerJob " + new Date() + "-------------------------------------");
	}
}