package com.app.quartz.engine.entity;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.app.quartz.engine.entity.converter.BooleanIntConverter;

@Proxy(lazy=false)
@Entity
@Table(name = "job_class_repository")
public class JobClassRepository {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String jobClass;
	
	private String jobName;
	@Convert(converter = BooleanIntConverter.class)
	private  Boolean jobActive;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Boolean getJobActive() {
		return jobActive;
	}
	public void setJobActive(Boolean jobActive) {
		this.jobActive = jobActive;
	}
	
}
