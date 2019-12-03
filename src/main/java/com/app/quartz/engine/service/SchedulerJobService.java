package com.app.quartz.engine.service;

import java.util.List;
import java.util.Map;

import com.app.quartz.engine.entity.SchedulerJobInfo;

public interface SchedulerJobService {

	void startAllSchedulers();

	void resumeAllSchedulers();

	List<SchedulerJobInfo> schedulerJobList();

	List<Map<String, Object>> schedulerJobMapList();

	boolean createScheduleJob(SchedulerJobInfo jobInfo);

	boolean updateScheduleJob(SchedulerJobInfo jobInfo);

	boolean deleteScheduleJob(SchedulerJobInfo jobInfo);

	boolean pauseScheduleJob(SchedulerJobInfo jobInfo);

	boolean resumeScheduleJob(SchedulerJobInfo jobInfo);

	boolean startJobNow(SchedulerJobInfo jobInfo);

	boolean unScheduleJob(String jobName);

	List<Map<String, Object>> getAllJobs();

	boolean isJobRunning(String jobName, String groupKey);

	String getJobState(String jobName, String groupKey);

}
