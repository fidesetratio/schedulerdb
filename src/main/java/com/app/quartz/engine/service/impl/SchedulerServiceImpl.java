package com.app.quartz.engine.service.impl;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.quartz.CronExpression;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.quartz.engine.component.JobScheduleCreator;
import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.repository.SchedulerRepository;
import com.app.quartz.engine.service.SchedulerService;

@Slf4j
@Transactional
@Service
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private SchedulerRepository schedulerRepository;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private JobScheduleCreator scheduleCreator;

	@Override
	public void startAllSchedulers() {
		List<SchedulerJobInfo> jobInfoList = schedulerRepository.findAll();

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
	public void scheduleNewJob(SchedulerJobInfo jobInfo) {
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
				schedulerRepository.save(jobInfo);
			} else {
			}
		} catch (ClassNotFoundException e) {
		} catch (SchedulerException e) {
		}
	}

	@Override
	public void updateScheduleJob(SchedulerJobInfo jobInfo) {
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
			schedulerRepository.save(jobInfo);
		} catch (SchedulerException e) {
		}
	}

	@Override
	public boolean unScheduleJob(String jobName) {
		try {
			return schedulerFactoryBean.getScheduler().unscheduleJob(new TriggerKey(jobName));
		} catch (SchedulerException e) {
			return false;
		}
	}

	@Override
	public boolean deleteJob(SchedulerJobInfo jobInfo) {
		try {
			boolean ret = false;
			ret = schedulerFactoryBean.getScheduler()
					.deleteJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
			schedulerRepository.delete(jobInfo);
			return ret;
		} catch (SchedulerException e) {
			return false;
		}
	}

	@Override
	public boolean pauseJob(SchedulerJobInfo jobInfo) {
		try {
			schedulerFactoryBean.getScheduler().pauseJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
			return true;
		} catch (SchedulerException e) {
			return false;
		}
	}

	@Override
	public boolean resumeJob(SchedulerJobInfo jobInfo) {
		try {
			schedulerFactoryBean.getScheduler().resumeJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
			return true;
		} catch (SchedulerException e) {
			return false;
		}
	}

	@Override
	public boolean startJobNow(SchedulerJobInfo jobInfo) {
		try {
			schedulerFactoryBean.getScheduler().triggerJob(new JobKey(jobInfo.getJobName(), jobInfo.getJobGroup()));
			return true;
		} catch (SchedulerException e) {
			return false;
		}
	}
}