package com.app.quartz.engine.jobs;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.stream.IntStream;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.app.quartz.engine.util.RestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisallowConcurrentExecution
public class BirthdayGreetingJob extends QuartzJobBean {

	private static final Logger logger = LoggerFactory.getLogger(BirthdayGreetingJob.class);
	
	@Autowired
	private RestClient restClient;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("-------------------------- Start BirthdayGreetingJob " + new Date() + "-------------------------------------");
		
		// get job scheduler info data
//		JobDataMap map = context.getMergedJobDataMap();
//		String url = map.getString("url");
//		String httpMethod = map.getString("httpMethod");
//		
//		try {
//			String response = restClient.restClientOutput(url, HttpMethod.valueOf(httpMethod.toUpperCase()), null);
//		} catch (URISyntaxException e) {
//			logger.debug("BirthdayGreetingJob.executeInternal");
//			e.printStackTrace();
//		}
		
		JobDataMap map = context.getMergedJobDataMap();
		System.out.println("job : " + context.getJobInstance().toString());
		
		System.out.println("-------------------------- End BirthdayGreetingJob " + new Date() + "-------------------------------------");
	}

}
