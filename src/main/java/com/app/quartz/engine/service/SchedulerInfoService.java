package com.app.quartz.engine.service;

import java.util.List;
import java.util.Map;

import com.app.quartz.engine.entity.SchedulerJobInfo;

public interface SchedulerInfoService {

	public boolean create(SchedulerJobInfo jobInfo);
	public List<Map<String, Object>> read();
	public boolean update(SchedulerJobInfo jobInfo);
	public boolean delete(SchedulerJobInfo jobInfo);
	public SchedulerJobInfo getInfoByName(String name);
	public SchedulerJobInfo getInfoByNameExceptId(String name, long id);
	
}
