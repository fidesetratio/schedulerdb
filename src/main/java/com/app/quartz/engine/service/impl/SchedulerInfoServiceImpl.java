package com.app.quartz.engine.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.quartz.engine.entity.SchedulerJobInfo;
import com.app.quartz.engine.repository.SchedulerInfoRepository;
import com.app.quartz.engine.service.SchedulerInfoService;
import com.app.quartz.engine.service.SchedulerJobService;

@Slf4j
@Transactional
@Service
public class SchedulerInfoServiceImpl implements SchedulerInfoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerInfoServiceImpl.class);

	@Autowired
	private SchedulerInfoRepository schedulerInfoRepository;

	@Autowired
	private SchedulerJobService schedulerJobService;

	@Override
	public boolean create(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		LOGGER.debug("--------------------- scheduler job info added ------------------");

		boolean result = false;
		try {
			schedulerInfoRepository.save(jobInfo);
			result = true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
		}

		return result;
	}

	@Override
	public List<Map<String, Object>> read() {
		// TODO Auto-generated method stub
		LOGGER.debug("--------------------- scheduler job info read ------------------");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			List<SchedulerJobInfo> jobInfoList = schedulerJobService.schedulerJobList();

			for (SchedulerJobInfo jobList : jobInfoList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cronExpression", jobList.getCronExpression());
				map.put("cronJob", jobList.getCronJob());
				map.put("jobClass", jobList.getJobClass());
				map.put("jobGroup", jobList.getJobGroup());
				map.put("jobName", jobList.getJobName());
				map.put("repeatTime", jobList.getRepeatTime());
				map.put("params", jobList.getParams());
				map.put("url", jobList.getUrl());
				list.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean update(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		LOGGER.debug("--------------------- scheduler job info update ------------------");

		try {
			SchedulerJobInfo jobsInfo = schedulerInfoRepository.getOne(jobInfo.getId());
			jobsInfo.setCronExpression(jobInfo.getCronExpression());
			jobsInfo.setCronJob(jobInfo.getCronJob());
			jobsInfo.setJobClass(jobInfo.getJobClass());
			jobsInfo.setJobGroup(jobInfo.getJobGroup());
			jobsInfo.setJobName(jobInfo.getJobName());
			jobsInfo.setParams(jobInfo.getParams());
			jobsInfo.setRepeatTime(jobInfo.getRepeatTime());
			jobsInfo.setUrl(jobInfo.getUrl());
			schedulerInfoRepository.save(jobsInfo);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean delete(SchedulerJobInfo jobInfo) {
		// TODO Auto-generated method stub
		LOGGER.debug("--------------------- scheduler job info delete ------------------");

		try {
			schedulerInfoRepository.deleteById(jobInfo.getId());
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}