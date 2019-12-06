package com.app.quartz.engine.dto;

import java.util.List;

public class AjaxRequestModel {

	private String jobProcess;
	private List<SchedulerJob> jobList;
	
	public String getJobProcess() {
		return jobProcess;
	}
	public void setJobProcess(String jobProcess) {
		this.jobProcess = jobProcess;
	}
	public List<SchedulerJob> getJobList() {
		return jobList;
	}
	public void setJobList(List<SchedulerJob> jobList) {
		this.jobList = jobList;
	}
}
