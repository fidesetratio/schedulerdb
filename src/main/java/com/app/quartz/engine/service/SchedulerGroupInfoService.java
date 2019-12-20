package com.app.quartz.engine.service;

import java.util.List;

import com.app.quartz.engine.entity.SchedulerGroupInfo;

public interface SchedulerGroupInfoService {

	public List<SchedulerGroupInfo> getAllGroup();
	public List<SchedulerGroupInfo> getAllGroupTable();
	public SchedulerGroupInfo createGroup(SchedulerGroupInfo schedulerGroupInfo);
	public SchedulerGroupInfo getGroupInfoByName(String groupName);
	public SchedulerGroupInfo getGroupInfoByNameExceptId(String groupName, long groupId);
	public SchedulerGroupInfo getGroupInfo(long groupId);
	public List<String> getAllGroupName();
}
