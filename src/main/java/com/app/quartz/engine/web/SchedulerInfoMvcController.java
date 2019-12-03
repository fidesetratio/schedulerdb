package com.app.quartz.engine.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.quartz.engine.dto.SchedulerJob;
import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.service.SchedulerJobMvcService;
import com.app.quartz.engine.service.SchedulerJobService;
import com.app.quartz.engine.util.ResultResponse;

@Controller
public class SchedulerInfoMvcController {

	@Autowired
	private SchedulerJobMvcService schedulerJobMvcService;
	
	@Autowired
	private SchedulerJobService schedulerJobService;

	/*
	 * Get List Job Scheduler
	 */
	@GetMapping("/schedulerlist")
	public String listJob(Model model) {
		List<SchedulerJob> listJob = schedulerJobMvcService.schedulerJobMvcList();
		System.out.println("schedulerJob size: " + listJob.size());
		model.addAttribute("jobList", listJob);
		return "job_list";
	}

	/*
	 * Get List Job Scheduler
	 */
	@PostMapping("/submit")
	public String submit(@Valid @ModelAttribute("schedulerJobInfo")SchedulerJobInfo schedulerJobInfo, 
			  BindingResult result, Model model) {
		String checkResult = ResultResponse.MANDATORY_FIELD;
		
		if (schedulerJobInfo.getJobClass() == null || schedulerJobInfo.getJobClass().equals("")) 
			checkResult = ResultResponse.MANDATORY_FIELD;

		if (schedulerJobInfo.getJobName() == null || schedulerJobInfo.getJobName().equals("")) 
			checkResult = ResultResponse.MANDATORY_FIELD;

		if (schedulerJobInfo.getUrl() == null || schedulerJobInfo.getUrl().equals("")) 
			checkResult = ResultResponse.MANDATORY_FIELD;

		schedulerJobService.createScheduleJob(schedulerJobInfo);

		return "create_job";
	}
	
	/*
	 * Get List Job Scheduler
	 */
	@GetMapping("/createjob")
	public String createJob(Model model) {
		model.addAttribute("schedulerJobInfo", new SchedulerJobInfo());
		return "create_job";
	}

}
