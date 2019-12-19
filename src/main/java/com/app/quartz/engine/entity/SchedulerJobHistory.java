package com.app.quartz.engine.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "scheduler_job_history")
public class SchedulerJobHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long sjhId;
	private String sjhJobName;
	private String sjhJobGroup;
	private String sjhTriggerName;
	private String sjhHttpMethod;
	private String sjhUrl;
	private String sjhParams;
	private String sjhRequestBody;
	private String sjhResponseStatus;
	private String sjhResponseBody;
	
	public long getSjhId() {
		return sjhId;
	}
	public void setSjhId(long sjhId) {
		this.sjhId = sjhId;
	}
	public String getSjhJobName() {
		return sjhJobName;
	}
	public void setSjhJobName(String sjhJobName) {
		this.sjhJobName = sjhJobName;
	}
	public String getSjhJobGroup() {
		return sjhJobGroup;
	}
	public void setSjhJobGroup(String sjhJobGroup) {
		this.sjhJobGroup = sjhJobGroup;
	}
	public String getSjhTriggerName() {
		return sjhTriggerName;
	}
	public void setSjhTriggerName(String sjhTriggerName) {
		this.sjhTriggerName = sjhTriggerName;
	}
	public String getSjhHttpMethod() {
		return sjhHttpMethod;
	}
	public void setSjhHttpMethod(String sjhHttpMethod) {
		this.sjhHttpMethod = sjhHttpMethod;
	}
	public String getSjhUrl() {
		return sjhUrl;
	}
	public void setSjhUrl(String sjhUrl) {
		this.sjhUrl = sjhUrl;
	}
	public String getSjhParams() {
		return sjhParams;
	}
	public void setSjhParams(String sjhParams) {
		this.sjhParams = sjhParams;
	}
	public String getSjhRequestBody() {
		return sjhRequestBody;
	}
	public void setSjhRequestBody(String sjhRequestBody) {
		this.sjhRequestBody = sjhRequestBody;
	}
	public String getSjhResponseStatus() {
		return sjhResponseStatus;
	}
	public void setSjhResponseStatus(String sjhResponseStatus) {
		this.sjhResponseStatus = sjhResponseStatus;
	}
	public String getSjhResponseBody() {
		return sjhResponseBody;
	}
	public void setSjhResponseBody(String sjhResponseBody) {
		this.sjhResponseBody = sjhResponseBody;
	}
	
	@Override
	public String toString() {
		return "SchedulerJobHistory [sjhId=" + sjhId + ", sjhJobName=" + sjhJobName + ", sjhJobGroup=" + sjhJobGroup
				+ ", sjhTriggerName=" + sjhTriggerName + ", sjhHttpMethod=" + sjhHttpMethod + ", sjhUrl=" + sjhUrl
				+ ", sjhParams=" + sjhParams + ", sjhRequestBody=" + sjhRequestBody + ", sjhResponseStatus="
				+ sjhResponseStatus + ", sjhResponseBody=" + sjhResponseBody + "]";
	}
}