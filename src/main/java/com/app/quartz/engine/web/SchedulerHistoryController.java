package com.app.quartz.engine.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.quartz.engine.service.SchedulerJobHistoryService;

@Controller
@RequestMapping("/history")
public class SchedulerHistoryController {
	
	@Autowired
	private SchedulerJobHistoryService schedulerJobHistoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String groupList(Model model) {
		model.addAttribute("schedulerHistorylist", schedulerJobHistoryService.getAllHistory());
		
		return "history/job_history";
	}
}
