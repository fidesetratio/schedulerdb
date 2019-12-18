package com.app.quartz.engine.service.impl;

import java.net.URISyntaxException;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.quartz.engine.entity.SchedulerJobHistory;
import com.app.quartz.engine.repository.SchedulerJobHistoryRepository;
import com.app.quartz.engine.service.GenericMethodService;
import com.app.quartz.engine.util.RestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GenericMethodServiceImpl implements GenericMethodService {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerJobServiceImpl.class);
	
	@Autowired
	private RestClient restClient;
	
	@Autowired
	private SchedulerJobHistoryRepository schedulerJobHistoryRepository;
	
	@Override
	@Async
	public void requestURLClient(JobExecutionContext context) {
		JobDataMap map = context.getMergedJobDataMap();
		String url = map.getString("url");
		String httpMethod = map.getString("httpMethod");
		String params = map.getString("params");
		String requestBody = map.getString("requestBody");
		
		if (params != null && !params.isEmpty()) {
			url += params;
		}
		
		try {
			ResponseEntity<String> response = restClient.restClientOutput(url, HttpMethod.valueOf(httpMethod.toUpperCase()), requestBody);
			System.out.println("rest client response : " + response);
			
			SchedulerJobHistory history = new SchedulerJobHistory();
			history.setSjhJobName(context.getJobDetail().getKey().getName());
			history.setSjhJobGroup(context.getJobDetail().getKey().getGroup());
			history.setSjhTriggerName(context.getTrigger().getKey().getName());
			history.setSjhHttpMethod(httpMethod);
			history.setSjhUrl(url);
			history.setSjhParams(params);
			history.setSjhRequestBody(requestBody);
			history.setSjhResponseStatus(response.getStatusCode().toString());
			history.setSjhResponseBody(response.getBody());
			
			schedulerJobHistoryRepository.saveAndFlush(history);
		} catch (URISyntaxException e) {
			logger.debug("BirthdayGreetingJob.executeInternal");
			e.printStackTrace();
		}
	}

}
