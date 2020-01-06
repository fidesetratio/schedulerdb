package com.app.quartz.engine.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.quartz.engine.entity.NotificationsConfiguration;
import com.app.quartz.engine.entity.NotificationsHistory;
import com.app.quartz.engine.obj.NotificationType;
import com.app.quartz.engine.service.NotificationsConfigurationService;
import com.app.quartz.engine.service.NotificationsHistoryService;

@Controller
@RequestMapping("/notification")
public class SchedulerNotificationController {
	
	@Autowired
	private NotificationsConfigurationService notificationsConfigurationService;
	
	@Autowired
	private NotificationsHistoryService notificationsHistoryService;
	
	/**
	 * Get all notifications configuration for notification list table
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String notifications(Model model) {
		model.addAttribute("notificationList", notificationsConfigurationService.getAllNotifications());
	
		return "notification/notification_list";
	}
	
	@RequestMapping(value = "/{nhId}", method = RequestMethod.GET)
	public String getNotificationHistoryDetail(@PathVariable("nhId") long nhId, Model model) {
		NotificationsHistory notificationsHistory = notificationsHistoryService.getNotificationHistoryById(nhId);
		
		model.addAttribute("notificationsHistory", notificationsHistory);
		
		return "notification/notification_history_detail";
	}
	
	/**
	 * Get all notifications history for notification history table
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String notificationsHistory(Model model) {
		model.addAttribute("notificationHistorylist", notificationsHistoryService.getAllnotificationHistory());
	
		return "notification/notification_history";
	}

	/**
	 * View create and edit notification
	 * @param notifId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET, headers = "Accept=application/json")
	public String notification(@RequestParam("notifId") String notifId, Model model) {
		NotificationsConfiguration notificationsConfiguration = new NotificationsConfiguration();
		String title = "Create";
		
		if (notifId != null && !notifId.isEmpty()) {
			notificationsConfiguration = notificationsConfigurationService.getConfigById(Long.parseLong(notifId));
			title = "Edit";
		}
			
		model.addAttribute("notificationsConfiguration", notificationsConfiguration);
		model.addAttribute("notificationType", NotificationType.values());
		model.addAttribute("title", title);
		
		return "notification/notification_detail";
	}
	
	/**
	 * submit notification (create and edit)
	 * @param notificationsConfiguration
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST, headers = "Accept=application/json")
	public String submit(@Valid @ModelAttribute("notificationsConfiguration")NotificationsConfiguration notificationsConfiguration, 
			  BindingResult bindingResult, Model model) {
		String title = "Create";
		model.addAttribute("notificationType", NotificationType.values());
		
		if (notificationsConfiguration.getNcId() != 0) {
			title = "Edit";
		}
		
		model.addAttribute("title", title);
		List<ObjectError> objectErrorlist = bindingResult.getAllErrors();
		
		if (objectErrorlist != null && objectErrorlist.size() > 0) {
			model.addAttribute("errors", objectErrorlist);
			return "notification/notification_detail";
		} else {
			notificationsConfigurationService.saveNotificationsConfiguration(notificationsConfiguration);
			return "redirect:/notification";
		}
	}
	
}
