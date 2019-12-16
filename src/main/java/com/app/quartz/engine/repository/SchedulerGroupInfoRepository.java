package com.app.quartz.engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.quartz.engine.entity.SchedulerGroupInfo;

@Repository
public interface SchedulerGroupInfoRepository extends JpaRepository<SchedulerGroupInfo, Long> {
	
	public List<SchedulerGroupInfo> findAllByOrderByGroupNameAsc();

	@Query("FROM SchedulerGroupInfo sg WHERE LOWER(sg.groupName) LIKE :groupName ")
	public List<SchedulerGroupInfo> findByGroupName(@Param("groupName") String groupName);

	@Query("FROM SchedulerGroupInfo sg WHERE LOWER(sg.groupName) LIKE :groupName and sg.groupId <> :groupId ")
	public List<SchedulerGroupInfo> findByGroupNameExceptId(@Param("groupName") String groupName, @Param("groupId") long groupId);
}