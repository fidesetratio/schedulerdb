package com.app.quartz.engine.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Scheduler groups
 *NOTIFICATIONS_HISTORY
 *scheduler_group_info
 *SCHEDULER_GROUP_INFO
 */
@Proxy(lazy=false)
@Entity
@Table(name = "scheduler_group_info")
public class SchedulerGroupInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long groupId;
	
	private String groupName;
	
	private String description;
	
	@Transient
	private int totalJobs;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getTotalJobs() {
		return totalJobs;
	}

	public void setTotalJobs(int totalJobs) {
		this.totalJobs = totalJobs;
	}

	public JSONObject toJSON() {

        JSONObject jsonObject = new JSONObject();
        try {
			jsonObject.put("groupName", this.getGroupName());
			jsonObject.put("description", this.getDescription());
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
        return jsonObject;
    }

	@Override
	public String toString() {
		return "SchedulerGroupInfo [groupId=" + groupId + ", groupName=" + groupName + ", description=" + description
				+ ", totalJobs=" + totalJobs + ", getGroupId()=" + getGroupId() + ", getGroupName()=" + getGroupName()
				+ ", getDescription()=" + getDescription() + ", getTotalJobs()=" + getTotalJobs() + ", toJSON()="
				+ toJSON() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
