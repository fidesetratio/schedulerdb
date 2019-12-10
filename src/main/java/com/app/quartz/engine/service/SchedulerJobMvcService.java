package com.app.quartz.engine.service;

import java.util.List;

import com.app.quartz.engine.dto.SchedulerJob;
import com.app.quartz.engine.entity.SchedulerJobInfo;

public interface SchedulerJobMvcService {

	List<SchedulerJob> schedulerJobMvcList();

	boolean createSchedulerJobMvc(SchedulerJobInfo jobInfo);

}
