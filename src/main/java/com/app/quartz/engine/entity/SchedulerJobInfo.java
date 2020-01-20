package com.app.quartz.engine.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.URL;

import com.app.quartz.engine.dto.CronProperties;
import com.app.quartz.engine.entity.converter.BooleanStringConverter;
/**
 * JPA Model for scheduler job info
 * 
 * @author Patar.Tambunan
 * scheduler_job_info
 * SCHEDULER_JOB_INFO
 *
 */

@Entity
@Table(name = "scheduler_job_info")
public class SchedulerJobInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Job name can not be empty or null.")
	private String jobName;

	private String jobGroup;

	private String jobClass;
	
	private String jobClassName;



	private String cronExpression;
	
	private String cronInput;
	
	@Transient
	private CronProperties cronProperties;
	
	private Date startTime;
	
	private int repeatCount;
	
	private Long repeatInterval;

	@NotEmpty(message = "URL can not be empty or null.")
	@URL(message = "URL must be valid http:// or https://")
	private String url;

	@Convert(converter = BooleanStringConverter.class)
	private Boolean cronJob;

	private String params;
	
	@Transient
	private List<String> paramName;
	
	@Transient
	private List<String> paramInput;
	
	@NotEmpty(message = "Please choose HTTP Method")
	private String httpMethod;
	
	private String requestBody;
	
	public SchedulerJobInfo() {
		this.jobClass = "com.app.quartz.engine.jobs.GenericSchedulerJob";
		this.cronJob = true;
	}

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

	public String getCronInput() {
		return cronInput;
	}

	public void setCronInput(String cronInput) {
		this.cronInput = cronInput;
	}

	public CronProperties getCronProperties() {
		return cronProperties;
	}

	public void setCronProperties(CronProperties cronProperties) {
		this.cronProperties = cronProperties;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public Long getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(Long repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getCronJob() {
		return cronJob;
	}

	public void setCronJob(Boolean cronJob) {
		this.cronJob = cronJob;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public List<String> getParamName() {
		return paramName;
	}

	public void setParamName(List<String> paramName) {
		this.paramName = paramName;
	}

	public List<String> getParamInput() {
		return paramInput;
	}

	public void setParamInput(List<String> paramInput) {
		this.paramInput = paramInput;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	@Override
	public String toString() {
		return "SchedulerJobInfo [id=" + id + ", jobName=" + jobName + ", jobGroup=" + jobGroup + ", jobClass="
				+ jobClass + ", cronExpression=" + cronExpression + ", cronInput=" + cronInput + ", cronProperties="
				+ cronProperties + ", startTime=" + startTime + ", repeatCount=" + repeatCount + ", repeatInterval="
				+ repeatInterval + ", url=" + url + ", cronJob=" + cronJob + ", params=" + params + ", paramName="
				+ paramName + ", paramInput=" + paramInput + ", httpMethod=" + httpMethod + ", requestBody="
				+ requestBody + "]";
	}
	
	
	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}
	
	
}