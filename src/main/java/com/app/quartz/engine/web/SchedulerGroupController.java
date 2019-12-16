package com.app.quartz.engine.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.quartz.engine.dto.ServerResponse;
import com.app.quartz.engine.entity.SchedulerGroupInfo;
import com.app.quartz.engine.service.SchedulerGroupInfoService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/group")
public class SchedulerGroupController {
	
	@Autowired
	private SchedulerGroupInfoService schedulerGroupInfoService;

	@RequestMapping(method = RequestMethod.GET)
	public String groupList(Model model) {
		SchedulerGroupInfo schedulerGroupInfo = new SchedulerGroupInfo();
		List<SchedulerGroupInfo> groupList = schedulerGroupInfoService.getAllGroupTable();
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
			ObjectError oe = new ObjectError("groupName", "Group name is exist.");
			SchedulerGroupInfo existingGroup = null;
			if (schedulerGroupInfo.getGroupId() != null) {
				// check whether the group name is already used in edit group
				existingGroup = schedulerGroupInfoService.getGroupInfoByNameExceptId(schedulerGroupInfo.getGroupName(), schedulerGroupInfo.getGroupId());
			} else {
				// check whether the group name is already used in create new group
				existingGroup = schedulerGroupInfoService.getGroupInfoByName(schedulerGroupInfo.getGroupName());
			}
			if (existingGroup != null) {
				objectErrorlist.add(oe);
			}
		}
		
		if (objectErrorlist.size() > 0) {
			serverResponse.setStatusCode(200);
			serverResponse.setData(objectErrorlist.get(0).getDefaultMessage());
		} else {
			schedulerGroupInfoService.createGroup(schedulerGroupInfo);
			serverResponse.setStatusCode(200);
			serverResponse.setData("success");
		}
		return serverResponse;
	}
	
	@RequestMapping(value = "/{groupId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ServerResponse getGroupEditData(@PathVariable("groupId") long groupId) {
		SchedulerGroupInfo schedulerGroupinfo = schedulerGroupInfoService.getGroupInfo(groupId);
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.setStatusCode(200);
		
		Gson gson = new Gson();
		serverResponse.setData(gson.toJson(schedulerGroupinfo));
		return serverResponse;
	}
	
}
