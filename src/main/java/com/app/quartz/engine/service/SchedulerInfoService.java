package com.app.quartz.engine.service;

import java.util.List;
import java.util.Map;

import com.app.quartz.engine.entity.SchedulerJobInfo;

public interface SchedulerInfoService {

	boolean create(SchedulerJobInfo jobInfo);

	List<Map<String, Object>> read();

	boolean update(SchedulerJobInfo jobInfo);

	boolean delete(SchedulerJobInfo jobInfo);

}
