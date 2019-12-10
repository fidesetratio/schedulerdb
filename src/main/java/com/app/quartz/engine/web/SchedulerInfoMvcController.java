package com.app.quartz.engine.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.quartz.engine.dto.SchedulerJob;
import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.service.SchedulerJobMvcService;

@Controller
public class SchedulerInfoMvcController {

	@Autowired
	private SchedulerJobMvcService schedulerJobMvcService;

	/*
	 * Get List Job Scheduler
	 */
	@GetMapping("/schedulerlist")
	public String listJob(Model model) {
		List<SchedulerJob> listJob = schedulerJobMvcService.schedulerJobMvcList();
		System.out.println("scheduluerJob size: " + listJob.size());
		model.addAttribute("listJobs", listJob);
		return "job_list";
	}

	/*
	 * Get List Job Scheduler
	 */
	@GetMapping("/createjob")
	public String CreateJob(Model model) {
		return "create_job";
	}

	/*
	 * Create Scheduler Job
	 */
	@RequestMapping(value = "/schedulerjobsave", method = RequestMethod.POST)
	public String create(Model model, @ModelAttribute("schedulerjobinfo") SchedulerJobInfo job, BindingResult result) {
		System.out.println("--- masuk ke scheduler job save ---");
		System.out.println(job.getJobClass());
		System.out.println(job.getJobName());

//		schedulerJobMvcService.createSchedulerJobMvc(job);
		return "redirect:/schedulerlist";
	}

}
