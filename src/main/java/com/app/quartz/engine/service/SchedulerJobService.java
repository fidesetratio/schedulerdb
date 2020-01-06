package com.app.quartz.engine.service;

import java.util.List;
import java.util.Map;

import org.quartz.JobKey;

import com.app.quartz.engine.dto.SchedulerJob;
import com.app.quartz.engine.entity.SchedulerJobHistory;
import com.app.quartz.engine.entity.SchedulerJobInfo;

public interface SchedulerJobService {

	public void startAllSchedulers();
	public void resumeAllSchedulers();
	public void pauseAllSchedulers();
	public List<SchedulerJobInfo> schedulerJobList();
	public List<Map<String, Object>> schedulerJobMapList();
	public boolean createScheduleJob(SchedulerJobInfo jobInfo);
	public boolean updateScheduleJob(SchedulerJobInfo jobInfo);
	public boolean deleteScheduleJob(List<SchedulerJobInfo> schedulerJobInfo);
	public boolean pauseScheduleJob(SchedulerJobInfo schedulerJobInfo);
	public boolean resumeScheduleJob(SchedulerJobInfo schedulerJobInfo);
	public boolean startJobNow(SchedulerJobInfo jobInfo);
	public boolean unScheduleJob(SchedulerJobInfo jobInfo);
	public List<SchedulerJob> getAllJobs();
	public boolean isJobRunning(String jobName, String groupKey);
	public String getJobState(String jobName, String groupKey);
	public SchedulerJobInfo getJobInfo(JobKey jobKey);
	public List<SchedulerJob> searchSchedulerb(String jobName, String jobGroup);
	public int countTotalJobByGroup(String jobGroup);
	public boolean retryScheduler(SchedulerJobHistory schedulerJobHistory);
}
