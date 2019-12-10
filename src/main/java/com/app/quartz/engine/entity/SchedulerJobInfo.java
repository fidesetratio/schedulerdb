package com.app.quartz.engine.entity;

import java.util.HashMap;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.quartz.engine.entity.converter.BooleanStringConverter;
import com.app.quartz.engine.entity.converter.MapStringConverter;

/**
 * JPA Model for scheduler job info
 * 
 * @author Patar.Tambunan
 *
 */

@Entity
@Table(name = "scheduler_job_info")
public class SchedulerJobInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String jobName;

	private String jobGroup;

	private String jobClass;

	private String cronExpression;

	private Long repeatTime;

	private String url;

	@Convert(converter = BooleanStringConverter.class)
	private Boolean cronJob;

	@Convert(converter = MapStringConverter.class)
	private HashMap params;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Long getRepeatTime() {
		return repeatTime;
	}

	public void setRepeatTime(Long repeatTime) {
		this.repeatTime = repeatTime;
	}

	public Boolean getCronJob() {
		return cronJob;
	}

	public void setCronJob(Boolean cronJob) {
		this.cronJob = cronJob;
	}

	public HashMap getParams() {
		return params;
	}

	public void setParams(HashMap params) {
		this.params = params;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}