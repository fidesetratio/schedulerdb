package com.app.quartz.engine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quartz.engine.entity.SchedulerGroupInfo;
import com.app.quartz.engine.repository.SchedulerGroupInfoRepository;
import com.app.quartz.engine.service.SchedulerGroupInfoService;

@Service
public class SchedulerGroupInfoServiceImpl implements SchedulerGroupInfoService {

	@Autowired
	private SchedulerGroupInfoRepository schedulerGroupInfoRepository;
	
	@Override
	public List<SchedulerGroupInfo> getAllGroup() {
		return schedulerGroupInfoRepository.findAll();
	}

	@Override
	public SchedulerGroupInfo createGroup(SchedulerGroupInfo schedulerGroupInfo) {
		return schedulerGroupInfoRepository.saveAndFlush(schedulerGroupInfo);
	}

	@Override
	public boolean isExistGroupName(String groupName) {
		boolean check = false;
		List<SchedulerGroupInfo> list = schedulerGroupInfoRepository.findByGroupName(groupName);
		if (list != null && list.size() > 0) {
			check = true;
		}
		return check;
	}
}
