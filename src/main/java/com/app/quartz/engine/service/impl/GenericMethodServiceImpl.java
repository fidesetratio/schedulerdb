package com.app.quartz.engine.service.impl;

import java.net.URISyntaxException;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.app.quartz.engine.service.GenericMethodService;
import com.app.quartz.engine.util.RestClient;

@Service
public class GenericMethodServiceImpl implements GenericMethodService {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerJobServiceImpl.class);
	
	@Autowired
	private RestClient restClient;
	
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
