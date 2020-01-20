package com.app.quartz.engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quartz.engine.entity.JobClassRepository;

@Repository
public interface JobClassRepositoryRepository  extends JpaRepository<JobClassRepository, Long>{
	public List<JobClassRepository> findByJobActive(Boolean active );
	public List<JobClassRepository> findAll();
}
