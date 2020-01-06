package com.app.quartz.engine.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SchedulerCommonController {

	/**
	 * default url to schedulerdb redirect to list of job page
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String redirectJoblist() {
		return "redirect:/job";
	}
}
