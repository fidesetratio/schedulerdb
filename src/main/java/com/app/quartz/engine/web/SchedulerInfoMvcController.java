package com.app.quartz.engine.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
	
	private final String[] httpMethodlist = {RequestMethod.GET.toString(), RequestMethod.POST.toString(), RequestMethod.PUT.toString(), RequestMethod.DELETE.toString()};

	/**
	 * view job list and search job
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/joblist", method = { RequestMethod.GET, RequestMethod.POST }, headers = "Accept=application/json")
	public String listJob(HttpServletRequest request, Model model) {
		List<SchedulerJob> listJob = new ArrayList<SchedulerJob>();
		if (request.getParameter("jobSearchinput") != null && !request.getParameter("jobSearchinput").isEmpty()) {
			String jobSearchoption = request.getParameter("jobSearchoption");
			String jobSearchinput = request.getParameter("jobSearchinput");
			listJob = jobRequestProcessService.searchScheduler(jobSearchoption, jobSearchinput);
		} else {
			listJob = schedulerJobService.getAllJobs();
		}
		model.addAttribute("jobList", listJob);
		return "job_list";
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
		model.addAttribute("httpMethodlist", httpMethodlist);
		if (!jobName.isEmpty() && jobName != null && !groupName.isEmpty() && groupName != null) {
			JobKey jobKey = new JobKey(jobName, groupName);
			schedulerJobInfo = schedulerJobService.getJobInfo(jobKey);
			model.addAttribute("schedulerJobInfo", schedulerJobInfo);
			model.addAttribute("title", "Edit Job");
		} else {
			model.addAttribute("schedulerJobInfo", schedulerJobInfo);
			model.addAttribute("title", "Create Job");
		}
		return "job_detail";
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
		List<String> responseErrorlist = new ArrayList<String>();
		List<ObjectError> objectErrorlist = bindingResult.getAllErrors();
		for (ObjectError oe : objectErrorlist) {
			responseErrorlist.add(oe.getDefaultMessage());
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("schedulerJobInfo", schedulerJobInfo);
			model.addAttribute("httpMethodlist", httpMethodlist);
			model.addAttribute("errors", responseErrorlist);
			return "job_detail";
		} else {
			String params = "";
			if (schedulerJobInfo.getParamName() != null && !schedulerJobInfo.getParamName().isEmpty()) {
				params = generateURLparams(schedulerJobInfo.getParamName(), schedulerJobInfo.getParamInput());
			}
			schedulerJobInfo.setParams(params);
			schedulerJobService.createScheduleJob(schedulerJobInfo);
			return "redirect:/joblist";
		}
	}
	
	/**
	 * function button process
	 * @param ajaxRequestModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ajax", method = RequestMethod.POST)
	public String ajaxCall(@RequestBody AjaxRequestModel ajaxRequestModel, Model model) {
		jobRequestProcessService.jobRequestProcess(ajaxRequestModel);
		return "redirect:/joblist";
	}
	
	private String generateURLparams(List<String> str1, List<String> str2) {
		String params = "?";
		for (int i = 0; i < str1.size(); i++) {
			params += str1.get(i) + "=" + str2.get(i);
			if (i != str1.size() - 1) {
				params += "&";
			}
		}
		return params;
	}
}
