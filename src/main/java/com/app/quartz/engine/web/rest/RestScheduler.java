package com.app.quartz.engine.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.service.SchedulerService;

@RestController
@RequestMapping("/scheduler")
public class RestScheduler {

	@Autowired
	private SchedulerService schedulerService;

	@RequestMapping("/add")
	public ResponseEntity<String> add(@RequestBody SchedulerJobInfo jobInfo) {
		String okDesc = "ok";

		schedulerService.scheduleNewJob(jobInfo);
		ResponseEntity<String> ok = new ResponseEntity<String>(okDesc, HttpStatus.OK);
		return ok;
	}

	@RequestMapping("/remove")
	public ResponseEntity<String> remove(@RequestBody SchedulerJobInfo jobInfo) {
		String okDesc = "ok";
		schedulerService.deleteJob(jobInfo);
		ResponseEntity<String> ok = new ResponseEntity<String>(okDesc, HttpStatus.OK);
		return ok;
	}

	@RequestMapping("/pause")
	public ResponseEntity<String> pause(@RequestBody SchedulerJobInfo jobInfo) {
		String okDesc = "ok";
		schedulerService.pauseJob(jobInfo);
		ResponseEntity<String> ok = new ResponseEntity<String>(okDesc, HttpStatus.OK);
		return ok;
	}

	@RequestMapping("/resume")
	public ResponseEntity<String> resume(@RequestBody SchedulerJobInfo jobInfo) {
		String okDesc = "ok";
		schedulerService.resumeJob(jobInfo);
		ResponseEntity<String> ok = new ResponseEntity<String>(okDesc, HttpStatus.OK);
		return ok;
	}

	@RequestMapping("/reload")
	public ResponseEntity<String> reload() {
		String okDesc = "okyeye";
		schedulerService.startAllSchedulers();
		ResponseEntity<String> ok = new ResponseEntity<String>(okDesc, HttpStatus.OK);
		return ok;
	}

}
