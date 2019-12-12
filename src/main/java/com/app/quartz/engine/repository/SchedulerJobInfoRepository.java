package com.app.quartz.engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.quartz.engine.entity.SchedulerJobInfo;

@Repository
public interface SchedulerJobInfoRepository extends JpaRepository<SchedulerJobInfo, Long> {
	
	@Query("FROM SchedulerJobInfo sc where sc.jobName = :name AND sc.jobGroup = :groupName")
	public List<SchedulerJobInfo> findByNameAndGroup(@Param("name") String name, @Param("groupName") String groupName);

	@Query("FROM SchedulerJobInfo sc WHERE sc.jobName LIKE %:name% AND sc.jobGroup LIKE %:groupName% ")
	public List<SchedulerJobInfo> findByNameGroup(@Param("name") String name, @Param("groupName") String groupName);
}
