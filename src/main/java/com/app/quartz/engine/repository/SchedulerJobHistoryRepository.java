package com.app.quartz.engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quartz.engine.entity.SchedulerJobHistory;

@Repository
public interface SchedulerJobHistoryRepository extends JpaRepository<SchedulerJobHistory, Long> {
	
	public List<SchedulerJobHistory> findAllByOrderBySjhFireTimeDesc();
}
