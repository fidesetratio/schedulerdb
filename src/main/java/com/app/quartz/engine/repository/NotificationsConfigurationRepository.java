package com.app.quartz.engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.quartz.engine.entity.NotificationsConfiguration;

public interface NotificationsConfigurationRepository extends JpaRepository<NotificationsConfiguration, Long> {

	
	@Query("FROM NotificationsConfiguration nc WHERE nc.ncPlatform = :platform ")
	public List<NotificationsConfiguration> findByPlatform(@Param("platform") int platform);
}
