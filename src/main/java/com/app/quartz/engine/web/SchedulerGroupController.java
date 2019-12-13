package com.app.quartz.engine.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.quartz.engine.dto.ServerResponse;
import com.app.quartz.engine.entity.SchedulerGroupInfo;
import com.app.quartz.engine.service.SchedulerGroupInfoService;

@Controller
@RequestMapping("/group")
public class SchedulerGroupController {
	
	@Autowired
	private SchedulerGroupInfoService schedulerGroupInfoService;

	@RequestMapping(method = RequestMethod.GET)
	public String groupList(Model model) {
		SchedulerGroupInfo schedulerGroupInfo = new SchedulerGroupInfo();
		List<SchedulerGroupInfo> groupList = schedulerGroupInfoService.getAllGroup();
		model.addAttribute("title", "Create Group");
		model.addAttribute("groupList", groupList);
		model.addAttribute("schedulerGroupInfo", schedulerGroupInfo);
		return "group/group_list";
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServerResponse submit(@RequestBody SchedulerGroupInfo schedulerGroupInfo) {
		List<ObjectError> objectErrorlist = new ArrayList<ObjectError>();
		ServerResponse serverResponse = new ServerResponse();
		
		// check whether the group name is empty
		if (schedulerGroupInfo.getGroupName() == null || schedulerGroupInfo.getGroupName().isEmpty()) {
			ObjectError oe = new ObjectError("groupName", "Group name can not be empty or null.");
			objectErrorlist.add(oe);
		} else {
			// check whether the group name is already used 
			boolean groupNameisExist = schedulerGroupInfoService.isExistGroupName(schedulerGroupInfo.getGroupName());
			if (groupNameisExist) {
				ObjectError oe = new ObjectError("groupName", "Group name is exist.");
				objectErrorlist.add(oe);
			}
		}
		
		if (objectErrorlist.size() > 0) {
			serverResponse.setStatusCode(200);
			serverResponse.setData(objectErrorlist.get(0).getDefaultMessage());
		} else {
//			schedulerGroupInfoService.createGroup(schedulerGroupInfo);
			serverResponse.setStatusCode(200);
			serverResponse.setData("success");
		}
		System.out.println("schedulerGroupInfo: " + schedulerGroupInfo.toString());
		return serverResponse;
	}
	
}
