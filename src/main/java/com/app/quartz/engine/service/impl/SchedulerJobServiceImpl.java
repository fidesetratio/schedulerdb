package com.app.quartz.engine.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.quartz.CronExpression;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.quartz.engine.component.JobScheduleCreator;
import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.repository.SchedulerJobRepository;
import com.app.quartz.engine.service.SchedulerJobService;

@Slf4j
@Transactional
@Service
public class SchedulerJobServiceImpl implements SchedulerJobService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerJobServiceImpl.class);

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private JobScheduleCreator scheduleCreator;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private SchedulerJobRepository schedulerJobRepository;

	@Autowired
	private SchedulerJobService schedulerJobService;

	@Override
	public List<SchedulerJobInfo> schedulerJobList() {
		// TODO Auto-generated method stub
		List<SchedulerJobInfo> schedulerJobInfoList = null;
		try {
			schedulerJobInfoList = schedulerJobRepository.findAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return schedulerJobInfoList;
	}

	/*
	 * Create Job Scheduler
	 */
	@Override
	public boolean createScheduleJob(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			JobDetail jobDetail = JobBuilder
					.newJob((Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJobClass()))
					.withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();

			if (!scheduler.checkExists(jobDetail.getKey())) {
				jobDetail = scheduleCreator.createJob(
						(Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJobClass()), false, context,
						jobInfo.getJobName(), jobInfo.getJobGroup(), jobInfo.getParams(), jobInfo.getUrl(), jobInfo.getHttpMethod());

				Trigger trigger;

				if (jobInfo.getCronJob()) {
					trigger = scheduleCreator.createCronTrigger(jobInfo.getJobName(), new Date(),
							jobInfo.getCronExpression(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

				} else {
					trigger = scheduleCreator.createSimpleTrigger(jobInfo.getJobName(), new Date(),
							jobInfo.getRepeatTime(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

				}

				scheduler.scheduleJob(jobDetail, trigger);
				schedulerJobRepository.save(jobInfo);
				System.out.println("scheduler added sucessfully.... ");

				return true;

			} else {
				System.out.println("nothing to do.....");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException ==> " + e.getMessage());

		} catch (SchedulerException e) {
			System.out.println(e.getMessage());

		}

		return false;
	}

	/*
	 * Get List Job Scheduler
	 */
	@Override
	public List<Map<String, Object>> schedulerJobMapList() {
		// TODO Auto-generated method stub

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			List<SchedulerJobInfo> jobInfoList = schedulerJobService.schedulerJobList();

			for (SchedulerJobInfo jobList : jobInfoList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cronExpression", jobList.getCronExpression());
				map.put("cronJob", jobList.getCronJob());
				map.put("jobClass", jobList.getJobClass());
				map.put("jobGroup", jobList.getJobGroup());
				map.put("jobName", jobList.getJobName());
				map.put("repeatTime", jobList.getRepeatTime());
				map.put("params", jobList.getParams());
				map.put("url", jobList.getUrl());
				list.add(map);
			}

		} catch (Exception e) {
			System.out.println("error message :" + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * Update Job Scheduler
	 */
	@Override
	public boolean updateScheduleJob(SchedulerJobInfo jobInfo) {
		Trigger newTrigger;

		if (jobInfo.getCronJob()) {
			newTrigger = scheduleCreator.createCronTrigger(jobInfo.getJobName(), new Date(),
					jobInfo.getCronExpression(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);

		} else {
			newTrigger = scheduleCreator.createSimpleTrigger(jobInfo.getJobName(), new Date(), jobInfo.getRepeatTime(),
					SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
		}

		try {
			schedulerFactoryBean.getScheduler().rescheduleJob(TriggerKey.triggerKey(jobInfo.getJobName()), newTrigger);

			SchedulerJobInfo jobsInfo = schedulerJobRepository.getOne(jobInfo.getId());
			jobsInfo.setCronExpression(jobInfo.getCronExpression());
			jobsInfo.setCronJob(jobInfo.getCronJob());
			jobsInfo.setJobClass(jobInfo.getJobClass());
			jobsInfo.setJobGroup(jobInfo.getJobGroup());
			jobsInfo.setJobName(jobInfo.getJobName());
			jobsInfo.setParams(jobInfo.getParams());
			jobsInfo.setRepeatTime(jobInfo.getRepeatTime());
			jobsInfo.setUrl(jobInfo.getUrl());
			schedulerJobRepository.save(jobsInfo);

			return true;

		} catch (SchedulerException e) {
			System.out.println("SchedulerException " + e.getMessage());
		}

		return false;
	}

	/*
	 * Delete Job Scheduler
	 */
	@Override
	public boolean deleteScheduleJob(SchedulerJobInfo jobInfo) {
		try {
			boolean ret = true;
			ret = schedulerFactoryBean.getScheduler()
					.deleteJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
			schedulerJobRepository.deleteById(jobInfo.getId());

			LOGGER.debug("deleteJob succesfully....");
			return ret;
		} catch (SchedulerException e) {

		}
		return false;
	}

	/*
	 * Pause Job Scheduler
	 */
	@Override
	public boolean pauseScheduleJob(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		try {
			schedulerFactoryBean.getScheduler().pauseJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
			return true;
		} catch (SchedulerException e) {
			return false;
		}
	}

	/*
	 * Resume Job Scheduler
	 */
	@Override
	public boolean resumeScheduleJob(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		try {
			schedulerFactoryBean.getScheduler().resumeJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
			return true;
		} catch (SchedulerException e) {
			return false;
		}
	}

	/*
	 * Start Job Scheduler
	 */
	@Override
	public boolean startJobNow(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		try {
			schedulerFactoryBean.getScheduler().triggerJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
			return true;
		} catch (SchedulerException e) {
			return false;
		}
	}

	/*
	 * UnSchedule Job Scheduler
	 */
	@Override
	public boolean unScheduleJob(String jobName) {
		// TODO Auto-generated method stub
		try {
			return schedulerFactoryBean.getScheduler().unscheduleJob(new TriggerKey(jobName));
		} catch (SchedulerException e) {
			return false;
		}
	}

	/*
	 * Get All Job Scheduler
	 */
	@Override
	public List<Map<String, Object>> getAllJobs() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					// get job's trigger
					List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
					Date scheduleTime = triggers.get(0).getStartTime();
					Date nextFireTime = triggers.get(0).getNextFireTime();
					Date lastFiredTime = triggers.get(0).getPreviousFireTime();

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("jobName", jobName);
					map.put("groupName", jobGroup);
					map.put("scheduleTime", scheduleTime); // date
					map.put("lastFiredTime", lastFiredTime); // date
					map.put("nextFireTime", nextFireTime); // date

					if (isJobRunning(jobName, jobGroup)) {
						map.put("jobStatus", "RUNNING");
					} else {
						String jobState = getJobState(jobName, jobGroup);
						map.put("jobStatus", jobState);
					}

					list.add(map);
					System.out.println("Job details:");
					System.out.println(
							"Job Name:" + jobName + ", Group Name:" + groupName + ", Schedule Time:" + scheduleTime);
				}
			}
		} catch (SchedulerException e) {
			System.out.println("SchedulerException while fetching all jobs. error message :" + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Check if job is already running
	 */
	@Override
	public boolean isJobRunning(String jobName, String groupKey) {
		System.out.println("Request received to check if job is running");
		String jobKey = jobName;
		System.out.println("Parameters received for checking job is running now : jobKey :" + jobKey);

		try {
			List<JobExecutionContext> currentJobs = schedulerFactoryBean.getScheduler().getCurrentlyExecutingJobs();
			System.out.println("current job not null? ... " + currentJobs.size());

			if (currentJobs != null && currentJobs.size() > 0) {
				for (JobExecutionContext jobCtx : currentJobs) {
					String jobNameDB = jobCtx.getJobDetail().getKey().getName();
					String groupNameDB = jobCtx.getJobDetail().getKey().getGroup();
					if (jobKey.equalsIgnoreCase(jobNameDB) && groupKey.equalsIgnoreCase(groupNameDB)) {
						System.out.println("check job is running return true...");
						return true;
					}
				}
			}
		} catch (SchedulerException e) {
			System.out.println("SchedulerException while checking job with key :" + jobKey
					+ " is running. error message :" + e.getMessage());
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * Get the current state of job
	 */
	public String getJobState(String jobName, String groupKey) {
		System.out.println("jobName " + jobName + " groupKey " + groupKey);

		try {
			JobKey jobKey = new JobKey(jobName, groupKey);

			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);

			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobDetail.getKey());
			if (triggers != null && triggers.size() > 0) {
				for (Trigger trigger : triggers) {
					TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());

					if (TriggerState.PAUSED.equals(triggerState)) {
						return "PAUSED";
					} else if (TriggerState.BLOCKED.equals(triggerState)) {
						return "BLOCKED";
					} else if (TriggerState.COMPLETE.equals(triggerState)) {
						return "COMPLETE";
					} else if (TriggerState.ERROR.equals(triggerState)) {
						return "ERROR";
					} else if (TriggerState.NONE.equals(triggerState)) {
						return "NONE";
					} else if (TriggerState.NORMAL.equals(triggerState)) {
						return "SCHEDULED";
					}
				}
			}
		} catch (SchedulerException e) {
			System.out.println("SchedulerException while checking job with name and group exist:" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void startAllSchedulers() {
		List<SchedulerJobInfo> jobInfoList = schedulerJobRepository.findAll();

		if (jobInfoList != null) {
			System.out.println("runn..." + jobInfoList.size());
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			jobInfoList.forEach(jobInfo -> {
				try {
					JobDetail jobDetail = JobBuilder
							.newJob((Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJobClass()))
							.withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();
					System.out.println(jobDetail.getKey());
					if (!scheduler.checkExists(jobDetail.getKey())) {
						System.out.println("heh ngak ada");
						Trigger trigger;
						jobDetail = scheduleCreator.createJob(
								(Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJobClass()), false, context,
								jobInfo.getJobName(), jobInfo.getJobGroup(), jobInfo.getParams(), jobInfo.getUrl(), jobInfo.getHttpMethod());

						System.out.println("ngak mungkin gak lewat");
						System.out.println("cron ngak?" + jobInfo.getCronJob());

						if (jobInfo.getCronJob() && CronExpression.isValidExpression(jobInfo.getCronExpression())) {
							System.out.println("fire cron job now");
							trigger = scheduleCreator.createCronTrigger(jobInfo.getJobName(), new Date(),
									jobInfo.getCronExpression(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
						} else {
							trigger = scheduleCreator.createSimpleTrigger(jobInfo.getJobName(), new Date(),
									jobInfo.getRepeatTime(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
						}

						scheduler.scheduleJob(jobDetail, trigger);

					} else {
						System.out.println("already exist.");
					}

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SchedulerException e) {
					e.printStackTrace();
				}
			});
		}
	}

	@Override
	public void resumeAllSchedulers() {
		// TODO Auto-generated method stub
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();
					String jobState = getJobState(jobName, jobGroup);
					System.out.println("Job Name:" + jobName + ", Group Name:" + groupName + ", Job State " + jobState);

					if (jobState.equals("PAUSED")) {
						System.out.println("Resume Job Successfully.... ");
						schedulerFactoryBean.getScheduler().resumeJob(new JobKey(jobName, jobGroup));
					}
				}
			}

		} catch (SchedulerException e) {
			System.out.println("SchedulerException while fetching all jobs. error message :" + e.getMessage());
			e.printStackTrace();
		}
	}

}