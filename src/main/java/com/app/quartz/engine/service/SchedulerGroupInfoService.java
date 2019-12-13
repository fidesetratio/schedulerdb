package com.app.quartz.engine.service;

import java.util.List;

import com.app.quartz.engine.entity.SchedulerGroupInfo;

public interface SchedulerGroupInfoService {

	public List<SchedulerGroupInfo> getAllGroup();
	public SchedulerGroupInfo createGroup(SchedulerGroupInfo schedulerGroupInfo);
	public boolean isExistGroupName(String groupName);
}
