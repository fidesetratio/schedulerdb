package com.app.quartz.engine.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.quartz.engine.dto.SchedulerJob;
import com.app.quartz.engine.dto.ServerResponse;
import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.obj.ResultResponse;
import com.app.quartz.engine.obj.ServerResponseCode;
import com.app.quartz.engine.service.SchedulerJobService;

/**
 * not used
 *
 */
@RestController
@RequestMapping("/job")
public class SchedulerJobRestController {

	@Autowired
	private SchedulerJobService schedulerJobService;

	public ServerResponse getServerResponse(int responseCode, Object data) {
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setStatusCode(responseCode);
		serverResponse.setData(data);
		return serverResponse;
	}

	/*
	 * Create Job Scheduler
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ServerResponse jobInfoCreate(@RequestBody SchedulerJobInfo jobInfo) {
		String result = ResultResponse.MANDATORY_FIELD;
		boolean createResp = false;

		if (jobInfo.getCronJob() == null || jobInfo.getCronJob().equals("")) {
			result = ResultResponse.MANDATORY_FIELD;

		} else if (jobInfo.getJobClass() == null || jobInfo.getJobClass().equals("")) {
			result = ResultResponse.MANDATORY_FIELD;

		} else if (jobInfo.getJobName() == null || jobInfo.getJobName().equals("")) {
			result = ResultResponse.MANDATORY_FIELD;

		} else if (jobInfo.getJobGroup() == null || jobInfo.getJobGroup().equals("")) {
			result = ResultResponse.MANDATORY_FIELD;

		} else if (jobInfo.getUrl() == null || jobInfo.getUrl().equals("")) {
			result = ResultResponse.MANDATORY_FIELD;

		} else {

			if (CronExpression.isValidExpression(jobInfo.getCronExpression())) {
				createResp = schedulerJobService.createScheduleJob(jobInfo);

			} else {
				result = ResultResponse.NOT_VALID_EXPRESSION;

			}
		}

		if (createResp) {
			return getServerResponse(ServerResponseCode.SUCCESS, ResultResponse.INSERT_SUCCES);

		} else {
			return getServerResponse(ServerResponseCode.SUCCESS, result);

		}

	}

	/*
	 * Update Job Scheduler
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ServerResponse jobupdate(@RequestBody SchedulerJobInfo jobInfo) {
		boolean updateResp = schedulerJobService.updateScheduleJob(jobInfo);
		return getServerResponse(ServerResponseCode.SUCCESS, updateResp);
	}

	/*
	 * Delete Job Scheduler
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ServerResponse jobdelete(@RequestBody SchedulerJobInfo jobInfo) {
		List<SchedulerJobInfo> schedulerJobinfoList = new ArrayList<SchedulerJobInfo>();
		boolean deleteResp = schedulerJobService.deleteScheduleJob(schedulerJobinfoList);
		return getServerResponse(ServerResponseCode.SUCCESS, deleteResp);
	}

	/*
	 * Pause Job Scheduler
	 */
	@RequestMapping(value = "/pause", method = RequestMethod.POST)
	public ServerResponse pauseJob(@RequestBody SchedulerJobInfo jobInfo) {
		boolean pauseResp = schedulerJobService.pauseScheduleJob(jobInfo);
		return getServerResponse(ServerResponseCode.SUCCESS, pauseResp);
	}

	/*
	 * Resume Job Scheduler
	 */
	@RequestMapping(value = "/resume", method = RequestMethod.POST)
	public ServerResponse resumeJob(@RequestBody SchedulerJobInfo jobInfo) {
		boolean resumeResp = schedulerJobService.resumeScheduleJob(jobInfo);
		return getServerResponse(ServerResponseCode.SUCCESS, resumeResp);
	}

	/*
	 * Resume Job Scheduler
	 */
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public ServerResponse startJobNow(@RequestBody SchedulerJobInfo jobInfo) {
		boolean startJobResp = schedulerJobService.startJobNow(jobInfo);
		return getServerResponse(ServerResponseCode.SUCCESS, startJobResp);
	}

	/*
	 * Get List Job Scheduler
	 */
	@RequestMapping(value = "/listjob", method = RequestMethod.GET)
	public ServerResponse jobInfoList() {
		List<Map<String, Object>> list = schedulerJobService.schedulerJobMapList();
		return getServerResponse(ServerResponseCode.SUCCESS, list);
	}

	/*
	 * Resume Job Scheduler
	 */
	@RequestMapping(value = "/unschedule")
	public ServerResponse unschedulejob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup) {
		SchedulerJobInfo jobInfo = new SchedulerJobInfo();
		jobInfo.setJobName(jobName);
		jobInfo.setJobGroup(jobGroup);
		boolean uncsheduleResp = schedulerJobService.unScheduleJob(jobInfo);
		return getServerResponse(ServerResponseCode.SUCCESS, uncsheduleResp);
	}

	/*
	 * Get All Job Scheduler
	 */
	@RequestMapping("/jobs")
	public ServerResponse getAllJobs() {
		List<SchedulerJob> list = schedulerJobService.getAllJobs();
		return getServerResponse(ServerResponseCode.SUCCESS, list);
	}

	/*
	 * Is Job Running
	 */
	@RequestMapping("/isJobRunning")
	public ServerResponse isJobRunning(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup) {
		boolean status = schedulerJobService.isJobRunning(jobName, jobGroup);
		return getServerResponse(ServerResponseCode.SUCCESS, status);
	}

	/*
	 * Check Job State
	 */
	@RequestMapping("/jobState")
	public ServerResponse getJobState(@RequestParam("jobName") String jobName,
			@RequestParam("jobGroup") String jobGroup) {
		String jobState = schedulerJobService.getJobState(jobName, jobGroup);
		return getServerResponse(ServerResponseCode.SUCCESS, jobState);
	}

	/*
	 * Reload Job
	 */
	@RequestMapping("/jobReload")
	public ServerResponse jobReload() {
		schedulerJobService.startAllSchedulers();
		return getServerResponse(ServerResponseCode.SUCCESS, true);
	}

	/*
	 * Resume Job
	 */
	@RequestMapping("/jobAllResume")
	public ServerResponse resumeJob() {
		schedulerJobService.resumeAllSchedulers();
		return getServerResponse(ServerResponseCode.SUCCESS, true);
	}

}
