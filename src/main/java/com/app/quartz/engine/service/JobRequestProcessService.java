package com.app.quartz.engine.service;

import java.util.List;

import com.app.quartz.engine.dto.AjaxRequestModel;
import com.app.quartz.engine.dto.SchedulerJob;

public interface JobRequestProcessService {
	
	public void jobRequestProcess(AjaxRequestModel ajaxRequestModel);
	public List<SchedulerJob> searchScheduler(String jobSearchoption, String jobSearchinput);
}
