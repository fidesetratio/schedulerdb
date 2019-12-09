package com.app.quartz.engine.service;

import org.quartz.JobExecutionContext;

import com.app.quartz.engine.dto.AjaxRequestModel;

public interface JobRequestProcessService {
	
	public void jobRequestProcess(AjaxRequestModel ajaxRequestModel);
	public void requestURLClient(JobExecutionContext context);
}
