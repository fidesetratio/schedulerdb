package com.app.quartz.engine.web;

import java.util.List;

import javax.validation.Valid;

import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.quartz.engine.dto.AjaxRequestModel;
import com.app.quartz.engine.dto.SchedulerJob;
import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.service.JobRequestProcessService;
import com.app.quartz.engine.service.SchedulerJobService;

@Controller
public class SchedulerInfoMvcController {

	@Autowired
	private SchedulerJobService schedulerJobService;
	
	@Autowired
	private JobRequestProcessService jobRequestProcessService;

	/**
	 * view job list
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/joblist", method = RequestMethod.GET, headers = "Accept=application/json")
	public String listJob(Model model) {
		List<SchedulerJob> listJob = schedulerJobService.getAllJobs();
		model.addAttribute("jobList", listJob);
		return "job_list";
	}

	/**
	 * submit for create and update job 
	 * @param schedulerJobInfo
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST, headers = "Accept=application/json")
	public String submit(@Valid @ModelAttribute("schedulerJobInfo")SchedulerJobInfo schedulerJobInfo, 
			  BindingResult bindingResult, Model model) {
		String responseError = "";
		List<ObjectError> objectErrorlist = bindingResult.getAllErrors();
		for (ObjectError oe : objectErrorlist) {
			responseError += oe.getDefaultMessage() + ", ";
		}
		if (bindingResult.hasErrors()) {
			responseError += "can not be empty or null.";
			model.addAttribute("schedulerJobInfo", schedulerJobInfo);
			model.addAttribute("errors", responseError);
			return "job_detail";
		} else {
			schedulerJobService.createScheduleJob(schedulerJobInfo);
			return "redirect:/joblist";
		}
	}
	
	/**
	 * view create and update job page
	 * @param jobName
	 * @param groupName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/createjob", method = RequestMethod.GET, headers = "Accept=application/json")
	public String createJob(@RequestParam("jobName") String jobName, @RequestParam("groupName") String groupName, Model model) {
		SchedulerJobInfo schedulerJobInfo = new SchedulerJobInfo();
		model.addAttribute("jobGrouplist", schedulerJobService.getGroupList());
		if (!jobName.isEmpty() && jobName != null && !groupName.isEmpty() && groupName != null) {
			JobKey jobKey = new JobKey(jobName, groupName);
			schedulerJobInfo = schedulerJobService.getJobInfo(jobKey);
			model.addAttribute("schedulerJobInfo", schedulerJobInfo);
		} else {
			model.addAttribute("schedulerJobInfo", schedulerJobInfo);
		}
		return "job_detail";
	}
	
	/**
	 * function button process
	 * @param ajaxRequestModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ajax", method = RequestMethod.POST, headers = "Accept=application/json")
	public String ajaxCall(@RequestBody AjaxRequestModel ajaxRequestModel, Model model) {
		jobRequestProcessService.jobRequestProcess(ajaxRequestModel);
		return "redirect:/joblist";
	}
}
