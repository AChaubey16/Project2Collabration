package com.digital.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Jobs")
public class Job {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int jobId;
	@Column(nullable=false)
	private String jobName;
	@Column(nullable=false)
	private String skillsRequired;
	private String salary;
	private String jobDiscription;
	private String experienceReq;
	private String qualification;
	private String jobLocation;
	private Date   postedOn;
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getSkillsRequired() {
		return skillsRequired;
	}
	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getJobDiscription() {
		return jobDiscription;
	}
	public void setJobDiscription(String jobDiscription) {
		this.jobDiscription = jobDiscription;
	}
	public String getExperienceReq() {
		return experienceReq;
	}
	public void setExperienceReq(String experienceReq) {
		this.experienceReq = experienceReq;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getJobLocation() {
		return jobLocation;
	}
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
}
