package com.app.quartz.engine.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
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
import com.app.quartz.engine.repository.SchedulerJobRepository;
import com.app.quartz.engine.service.SchedulerJobMvcService;

@Slf4j
@Transactional
@Service
public class SchedulerJobServiceMvcImpl implements SchedulerJobMvcService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerJobServiceMvcImpl.class);

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private JobScheduleCreator scheduleCreator;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private SchedulerJobRepository schedulerJobRepository;

	@Override
	public List<SchedulerJob> schedulerJobMvcList() {
		List<SchedulerJob> list = new ArrayList<>();
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
					String jobState = getJobState(jobName, jobGroup);
					jobObj.setJobState(jobState);
					list.add(jobObj);
				}
			}
		} catch (SchedulerException e) {
			System.out.println("SchedulerException while fetching all jobs. error message :" + e.getMessage());
			e.printStackTrace();
		}
		return list;
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
	public boolean createSchedulerJobMvc(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();

			JobDetail jobDetail = JobBuilder
					.newJob((Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJobClass()))
					.withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup()).build();

			if (!scheduler.checkExists(jobDetail.getKey())) {
				jobDetail = scheduleCreator.createJob(
						(Class<? extends QuartzJobBean>) Class.forName(jobInfo.getJobClass()), false, context,
						jobInfo.getJobName(), jobInfo.getJobGroup(), jobInfo.getParams(), jobInfo.getUrl());

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
				System.out.println("scheduler mvc added sucessfully.... ");

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

}