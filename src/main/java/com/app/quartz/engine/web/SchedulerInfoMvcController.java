package com.app.quartz.engine.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.quartz.engine.dto.AjaxRequestModel;
import com.app.quartz.engine.dto.SchedulerJob;
import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.service.JobRequestProcessService;
import com.app.quartz.engine.service.SchedulerJobService;
import com.app.quartz.engine.util.ResultResponse;

@Controller
public class SchedulerInfoMvcController {

	@Autowired
	private SchedulerJobService schedulerJobService;
	
	@Autowired
	private JobRequestProcessService jobRequestProcessService;

	@RequestMapping(value = "/schedulerlist", method = RequestMethod.GET, headers = "Accept=application/json")
	public String listJob(Model model) {
		List<SchedulerJob> listJob = schedulerJobService.getAllJobs();
		model.addAttribute("jobList", listJob);
		return "job_list";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST, headers = "Accept=application/json")
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
	
	@RequestMapping(value = "/createjob", method = RequestMethod.GET, headers = "Accept=application/json")
	public String createJob(Model model) {
		model.addAttribute("schedulerJobInfo", new SchedulerJobInfo());
		return "create_job";
	}
	
	@RequestMapping(value = "/ajax", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String ajaxCall(@RequestBody AjaxRequestModel ajaxRequestModel) {
		jobRequestProcessService.jobRequestProcess(ajaxRequestModel);
		return "success";
	}

}
