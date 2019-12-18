package com.app.quartz.engine.web.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.quartz.engine.dto.ServerResponse;
import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.service.SchedulerInfoService;
import com.app.quartz.engine.util.ServerResponseCode;

/**
 * not used
 *
 */
@RestController
@RequestMapping("/info")
public class SchedulerInfoController {

	@Autowired
	private SchedulerInfoService schedulerInfoService;

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
		boolean updateResp = schedulerInfoService.create(jobInfo);
		return getServerResponse(ServerResponseCode.SUCCESS, updateResp);
	}

	/*
	 * Get List Job Scheduler
	 */
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public ServerResponse jobInfoList() {
		List<Map<String, Object>> list = schedulerInfoService.read();
		return getServerResponse(ServerResponseCode.SUCCESS, list);
	}

	/*
	 * Update Job Scheduler
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ServerResponse jobupdate(@RequestBody SchedulerJobInfo jobInfo) {
		boolean updateResp = schedulerInfoService.update(jobInfo);
		return getServerResponse(ServerResponseCode.SUCCESS, updateResp);
	}

	/*
	 * Delete Job Scheduler
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ServerResponse jobdelete(@RequestBody SchedulerJobInfo jobInfo) {
		boolean deleteResp = schedulerInfoService.delete(jobInfo);
		return getServerResponse(ServerResponseCode.SUCCESS, deleteResp);
	}

}
