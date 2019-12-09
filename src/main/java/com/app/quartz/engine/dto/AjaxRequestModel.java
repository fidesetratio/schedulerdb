package com.app.quartz.engine.dto;

import java.util.List;

import com.app.quartz.engine.entity.SchedulerJobInfo;

public class AjaxRequestModel {

	private String jobProcess;
	private List<SchedulerJobInfo> jobList;
	
	public String getJobProcess() {
		return jobProcess;
	}
	public void setJobProcess(String jobProcess) {
		this.jobProcess = jobProcess;
	}
	public List<SchedulerJobInfo> getJobList() {
		return jobList;
	}
	public void setJobList(List<SchedulerJobInfo> jobList) {
		this.jobList = jobList;
	}
	
}
