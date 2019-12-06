package com.app.quartz.engine.service;

import java.util.List;
import java.util.Map;

import org.quartz.JobKey;

import com.app.quartz.engine.dto.SchedulerJob;
import com.app.quartz.engine.entity.SchedulerJobInfo;

public interface SchedulerJobService {

	public void startAllSchedulers();
	public void resumeAllSchedulers();
	public List<SchedulerJobInfo> schedulerJobList();
	public List<Map<String, Object>> schedulerJobMapList();
	public boolean createScheduleJob(SchedulerJobInfo jobInfo);
	public boolean updateScheduleJob(SchedulerJobInfo jobInfo);
	public boolean deleteScheduleJob(SchedulerJobInfo jobInfo);
	public boolean pauseScheduleJob(JobKey jobKey);
	public boolean resumeScheduleJob(JobKey jobKey);
	public boolean startJobNow(SchedulerJobInfo jobInfo);
	public boolean unScheduleJob(String jobName);
	public List<SchedulerJob> getAllJobs();
	public boolean isJobRunning(String jobName, String groupKey);
	public String getJobState(String jobName, String groupKey);

}
