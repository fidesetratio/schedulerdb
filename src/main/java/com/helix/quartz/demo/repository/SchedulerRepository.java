package com.helix.quartz.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helix.quartz.demo.entity.SchedulerJobInfo;


@Repository
public interface SchedulerRepository extends JpaRepository<SchedulerJobInfo, Long> {

}
