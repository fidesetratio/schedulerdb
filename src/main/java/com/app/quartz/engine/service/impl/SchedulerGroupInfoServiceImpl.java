package com.app.quartz.engine.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.quartz.engine.entity.JobClassRepository;
import com.app.quartz.engine.entity.SchedulerGroupInfo;
import com.app.quartz.engine.repository.JobClassRepositoryRepository;
import com.app.quartz.engine.repository.SchedulerGroupInfoRepository;
import com.app.quartz.engine.service.SchedulerGroupInfoService;
import com.app.quartz.engine.service.SchedulerJobService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class SchedulerGroupInfoServiceImpl implements SchedulerGroupInfoService {

	@Autowired
	private SchedulerGroupInfoRepository schedulerGroupInfoRepository;
	
	@Autowired
	private JobClassRepositoryRepository jobClassRepositoryRepository;
	
	@Autowired
	private SchedulerJobService schedulerJobService;

	@Override
	public List<SchedulerGroupInfo> getAllGroup() {
		return schedulerGroupInfoRepository.findAllByOrderByGroupNameAsc();
	}

	@Override
	public SchedulerGroupInfo createGroup(SchedulerGroupInfo schedulerGroupInfo) {
		String groupName = schedulerGroupInfo.getGroupName().trim();
		schedulerGroupInfo.setGroupName(groupName);
		return schedulerGroupInfoRepository.saveAndFlush(schedulerGroupInfo);
	}

	@Override
	public SchedulerGroupInfo getGroupInfoByName(String groupName) {
		SchedulerGroupInfo groupInfo = null;
		List<SchedulerGroupInfo> list = schedulerGroupInfoRepository.findByGroupName(groupName.trim().toLowerCase());
		
		if (list != null && list.size() > 0) {
			groupInfo = list.get(0);
		}
		
		return groupInfo;
	}

	@Override
	public SchedulerGroupInfo getGroupInfo(long groupId) {
		return schedulerGroupInfoRepository.getOne(groupId);
	}

	@Override
	public SchedulerGroupInfo getGroupInfoByNameExceptId(String groupName, long groupId) {
		return schedulerGroupInfoRepository.findByGroupNameExceptId(groupName.trim().toLowerCase(), groupId).get(0);
	}

	@Override
	public List<SchedulerGroupInfo> getAllGroupTable() {
		List<SchedulerGroupInfo> groupInfolist = schedulerGroupInfoRepository.findAllByOrderByGroupNameAsc();
		
		if (groupInfolist != null && groupInfolist.size() > 0) {
			for (SchedulerGroupInfo sc : groupInfolist) {
				sc.setTotalJobs(schedulerJobService.countTotalJobByGroup(sc.getGroupName()));
			}
		}
		
		return groupInfolist;
	}

	@Override
	public List<String> getAllGroupName() {
		List<String> list = new ArrayList<String>();
		List<SchedulerGroupInfo> listGroup = schedulerGroupInfoRepository.findAllByOrderByGroupNameAsc();
		for (SchedulerGroupInfo group: listGroup) {
			list.add(group.getGroupName());
		}
		
		return list;
	}

	@Override
	public List<JobClassRepository> findActiveJobRepository() {
		return jobClassRepositoryRepository.findAll();
	}

	@Override
	public Map getJobsName() {
		
		Map<Long,String> jobs = new LinkedHashMap<Long,String>();
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		List<JobClassRepository> jobNames = this.findActiveJobRepository();
		for (JobClassRepository group: jobNames) {
			jobs.put(group.getId(), group.getJobName());
		}
		return jobs;
	}
}
