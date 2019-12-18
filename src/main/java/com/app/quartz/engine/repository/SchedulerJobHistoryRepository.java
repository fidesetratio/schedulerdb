package com.app.quartz.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.quartz.engine.entity.SchedulerJobHistory;

public interface SchedulerJobHistoryRepository extends JpaRepository<SchedulerJobHistory, Long> {
	
}
