package com.app.quartz.engine.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
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
import com.app.quartz.engine.dto.SchedulerJob;
import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.repository.SchedulerJobInfoRepository;
import com.app.quartz.engine.service.SchedulerJobService;

@Slf4j
@Transactional
@Service
public class SchedulerJobServiceImpl implements SchedulerJobService {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerJobServiceImpl.class);

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private JobScheduleCreator scheduleCreator;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private SchedulerJobInfoRepository schedulerJobInfoRepository;

	@Autowired
	private SchedulerJobService schedulerJobService;

	@Override
	public List<SchedulerJobInfo> schedulerJobList() {
		// TODO Auto-generated method stub
		List<SchedulerJobInfo> schedulerJobInfoList = null;
		try {
			schedulerJobInfoList = schedulerJobInfoRepository.findAll();
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
							jobInfo.getCronExpression(), CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);

				} else {
					trigger = scheduleCreator.createSimpleTrigger(jobInfo.getJobName(), jobInfo.getStartTime(),
							jobInfo.getRepeatInterval(), jobInfo.getRepeatCount(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
				}

				scheduler.scheduleJob(jobDetail, trigger);
				schedulerJobInfoRepository.save(jobInfo);
				logger.debug("scheduler added sucessfully....");

				return true;

			} else {
				this.updateScheduleJob(jobInfo);
			}

		} catch (ClassNotFoundException e) {
			logger.debug("SchedulerJobServiceImpl:createScheduleJob. ClassNotFoundException ==> " + e.getMessage());

		} catch (SchedulerException e) {
			logger.debug("SchedulerJobServiceImpl:createScheduleJob." + e.getMessage());
		}
		return false;
	}

	/*
	 * Get List Job Scheduler
	 */
	@Override
	public List<Map<String, Object>> schedulerJobMapList() {

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
				map.put("repeatTime", jobList.getRepeatInterval());
				map.put("params", jobList.getParams());
				map.put("url", jobList.getUrl());
				list.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	@Override
	public boolean updateScheduleJob(SchedulerJobInfo jobInfo) {
		Trigger newTrigger;

		if (jobInfo.getCronJob()) {
			newTrigger = scheduleCreator.createCronTrigger(jobInfo.getJobName(), new Date(),
					jobInfo.getCronExpression(), CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);

		} else {
			newTrigger = scheduleCreator.createSimpleTrigger(jobInfo.getJobName(), jobInfo.getStartTime(),
					jobInfo.getRepeatInterval(), jobInfo.getRepeatCount(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
		}

		try {
			Trigger triggerJob = schedulerFactoryBean.getScheduler().getTrigger(TriggerKey.triggerKey(jobInfo.getJobName()));
			schedulerFactoryBean.getScheduler().rescheduleJob(TriggerKey.triggerKey(jobInfo.getJobName()), newTrigger);

			SchedulerJobInfo jobsInfo = schedulerJobInfoRepository.findByNameAndGroup(jobInfo.getJobName(), jobInfo.getJobGroup()).get(0);
			jobsInfo.setCronExpression(jobInfo.getCronExpression());
			jobsInfo.setCronJob(jobInfo.getCronJob());
			jobsInfo.setJobClass(jobInfo.getJobClass());
			jobsInfo.setJobGroup(jobInfo.getJobGroup());
			jobsInfo.setJobName(jobInfo.getJobName());
			jobsInfo.setRepeatInterval(jobInfo.getRepeatInterval());
			jobsInfo.setParams(jobInfo.getParams());
			jobsInfo.setUrl(jobInfo.getUrl());
			schedulerJobInfoRepository.save(jobsInfo);

			return true;

		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		return false;
	}

	/*
	 * Delete Job Scheduler
	 */
	@Override
	public boolean deleteScheduleJob(List<SchedulerJobInfo> schedulerJobinfoList) {
		try {
			boolean ret = true;
			List<SchedulerJobInfo> listSchedulerJobInfo = new ArrayList<SchedulerJobInfo>();
			List<JobKey> jobKeys = new ArrayList<JobKey>();
			for (SchedulerJobInfo sc : schedulerJobinfoList) {
				jobKeys.add(new JobKey(sc.getJobName(), sc.getJobGroup()));
				SchedulerJobInfo schedulerJobInfo = schedulerJobInfoRepository.findByNameAndGroup(sc.getJobName(), sc.getJobGroup()).get(0);
				listSchedulerJobInfo.add(schedulerJobInfo);
			}
			ret = schedulerFactoryBean.getScheduler().deleteJobs(jobKeys);
			schedulerJobInfoRepository.deleteAll(listSchedulerJobInfo);
			
			return ret;
		} catch (SchedulerException e) {

		}
		return false;
	}
	
	@Override
	public boolean pauseScheduleJob(SchedulerJobInfo schedulerJobInfo) {
		try {
			schedulerFactoryBean.getScheduler().pauseJob(new JobKey(schedulerJobInfo.getJobName(), schedulerJobInfo.getJobGroup()));
			return true;
		} catch (SchedulerException e) {
			return false;
		}
	}

	/*
	 * Resume Job Scheduler
	 */
	@Override
	public boolean resumeScheduleJob(SchedulerJobInfo schedulerJobInfo) {
		try {
			schedulerFactoryBean.getScheduler().resumeJob(new JobKey(schedulerJobInfo.getJobName(), schedulerJobInfo.getJobGroup()));
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
	public boolean unScheduleJob(SchedulerJobInfo jobInfo) {
		try {
			return schedulerFactoryBean.getScheduler().unscheduleJob(TriggerKey.triggerKey(jobInfo.getJobName()));
		} catch (SchedulerException e) {
			return false;
		}
	}

	/*
	 * Get All Job Scheduler
	 */
	@Override
	public List<SchedulerJob> getAllJobs() {
		List<SchedulerJob> list = new ArrayList<SchedulerJob>();
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

					SchedulerJob jobObj = new SchedulerJob();
					jobObj.setJobName(jobName);
					jobObj.setGroupName(jobGroup);
					jobObj.setScheduleTime(scheduleTime);
					jobObj.setLastFiredTime(lastFiredTime);
					jobObj.setNextFireTime(nextFireTime);
					String jobState = "";
					
					if(isJobRunning(jobName, jobGroup)){
						jobState = "RUNNING";
					}else{
						jobState = getJobState(jobName, jobGroup);
					}
					jobObj.setJobState(jobState);

					
					list.add(jobObj);
				}
			}
		} catch (SchedulerException e) {
			logger.debug("SchedulerException while fetching all jobs. error message : " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Check if job is already running
	 */
	@Override
	public boolean isJobRunning(String jobName, String groupKey) {
		logger.debug("Request received to check if job is running");
		String jobKey = jobName;
		logger.debug("Parameters received for checking job is running now : jobKey :" + jobKey);

		try {
			List<JobExecutionContext> currentJobs = schedulerFactoryBean.getScheduler().getCurrentlyExecutingJobs();

			if (currentJobs != null && currentJobs.size() > 0) {
				for (JobExecutionContext jobCtx : currentJobs) {
					String jobNameDB = jobCtx.getJobDetail().getKey().getName();
					String groupNameDB = jobCtx.getJobDetail().getKey().getGroup();
					if (jobKey.equalsIgnoreCase(jobNameDB) && groupKey.equalsIgnoreCase(groupNameDB)) {
						return true;
					}
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * Get the current state of job
	 */
	public String getJobState(String jobName, String groupKey) {
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
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void startAllSchedulers() {
		List<SchedulerJobInfo> jobInfoList = schedulerJobInfoRepository.findAll();

		if (jobInfoList != null) {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			jobInfoList.forEach(jobInfo -> {
				try {
					JobDetail jobDetail = JobBuilder
							.newJob((Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJobClass()))
							.withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();
					if (!scheduler.checkExists(jobDetail.getKey())) {
						Trigger trigger;
						jobDetail = scheduleCreator.createJob(
								(Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJobClass()), false, context,
								jobInfo.getJobName(), jobInfo.getJobGroup(), jobInfo.getParams(), jobInfo.getUrl(), jobInfo.getHttpMethod());

						if (jobInfo.getCronJob() && CronExpression.isValidExpression(jobInfo.getCronExpression())) {
							trigger = scheduleCreator.createCronTrigger(jobInfo.getJobName(), new Date(),
									jobInfo.getCronExpression(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
						} else {
							trigger = scheduleCreator.createSimpleTrigger(jobInfo.getJobName(), jobInfo.getStartTime(),
									jobInfo.getRepeatInterval(), jobInfo.getRepeatCount(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
							
						}

						scheduler.scheduleJob(jobDetail, trigger);

					} else {
						logger.debug("already exist.");
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
		try {
			schedulerFactoryBean.getScheduler().resumeAll();

		} catch (SchedulerException e) {
			logger.debug("SchedulerException while fetching all jobs. error message :" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public SchedulerJobInfo getJobInfo(JobKey jobKey) {
		SchedulerJobInfo schedulerJobInfo = schedulerJobInfoRepository.findByNameAndGroup(jobKey.getName(), jobKey.getGroup()).get(0);
		if (schedulerJobInfo.getParams() != null && !schedulerJobInfo.getParams().isEmpty() && !schedulerJobInfo.getParams().equals("null")) {
			Map<String, List<String>> map =  generateParamsToMap(schedulerJobInfo.getParams());
			schedulerJobInfo.setParamName(map.get("keys"));
			schedulerJobInfo.setParamInput(map.get("values"));
		}
		return schedulerJobInfo;
	}

	@Override
	public void pauseAllSchedulers() {
		try {
			schedulerFactoryBean.getScheduler().pauseAll();
		} catch (SchedulerException e) {
			logger.debug("SchedulerJobService:pauseAllSchedulers.");
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getGroupList() {
		List<String> jobGrouplist = new ArrayList<String>();
		try {
			jobGrouplist = schedulerFactoryBean.getScheduler().getJobGroupNames();
		} catch (SchedulerException e) {
			logger.debug("SchedulerJobService:getGroupList.");
			e.printStackTrace();
		}
		return jobGrouplist;
	}

	@Override
	public List<SchedulerJob> searchSchedulerb(String inJobName, String inJobGroup) {
		List<SchedulerJobInfo> listSchedulerjobInfo = new ArrayList<SchedulerJobInfo>();
		List<SchedulerJob> listSchedulerjob = new ArrayList<SchedulerJob>();
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			listSchedulerjobInfo = schedulerJobInfoRepository.findByNameGroup(inJobName, inJobGroup);
			
			for (SchedulerJobInfo sc : listSchedulerjobInfo) {
					String jobName = sc.getJobName();
					String jobGroup = sc.getJobGroup();
					Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(jobName));
					Date scheduleTime = trigger.getStartTime();
					Date nextFireTime = trigger.getNextFireTime();
					Date lastFiredTime = trigger.getPreviousFireTime();

					SchedulerJob jobObj = new SchedulerJob();
					jobObj.setJobName(jobName);
					jobObj.setGroupName(jobGroup);
					jobObj.setScheduleTime(scheduleTime);
					jobObj.setLastFiredTime(lastFiredTime);
					jobObj.setNextFireTime(nextFireTime);
					String jobState = "";
					
					if(isJobRunning(jobName, jobGroup)){
						jobState = "RUNNING";
					}else{
						jobState = getJobState(jobName, jobGroup);
					}
					jobObj.setJobState(jobState);

					listSchedulerjob.add(jobObj);
			}
		} catch (SchedulerException e) {
			logger.debug("SchedulerJobService:searchSchedulerb.");
			e.printStackTrace();
		}
		return listSchedulerjob;
	}
	
	private Map<String, List<String>> generateParamsToMap(String str) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		String[] arrOfStr = str.split("\\?");
		String newString = arrOfStr[1];
		String[] newString1 = newString.split("\\&");
		for (String s : newString1) {
			String[] finalString = s.split("\\=");
			keys.add(finalString[0]);
			values.add(finalString[1]);
		}
		map.put("keys", keys);
		map.put("values", values);
		return map;
	}

}