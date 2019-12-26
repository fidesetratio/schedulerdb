package com.app.quartz.engine.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.quartz.engine.service.NotificationsConfigurationService;
import com.app.quartz.engine.service.NotificationsHistoryService;

@Controller
@RequestMapping("/notification")
public class SchedulerNotificationController {
	
	@Autowired
	private NotificationsConfigurationService notificationsConfigurationService;
	
	@Autowired
	private NotificationsHistoryService notificationsHistoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String notifications(Model model) {
		model.addAttribute("notificationList", notificationsConfigurationService.getAllNotifications());
	
		return "notification/notification_list";
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String notificationsHistory(Model model) {
		model.addAttribute("notificationHistorylist", notificationsHistoryService.getAllnotificationHistory());
	
		return "notification/notification_history";
	}
	
}
