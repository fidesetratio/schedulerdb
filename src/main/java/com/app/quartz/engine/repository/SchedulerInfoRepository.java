package com.app.quartz.engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quartz.engine.entity.SchedulerJobInfo;

@Repository
public interface SchedulerInfoRepository extends JpaRepository<SchedulerJobInfo, Long> {

}
