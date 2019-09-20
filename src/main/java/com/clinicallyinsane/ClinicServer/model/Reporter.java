package com.clinicallyinsane.ClinicServer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Reporter {
	
	private long id;
	private Integer leave;
	private Date startLeaveDate;
	private Date endLeaveDate;
	
	public Reporter() {
		super();
		
	}

	public Reporter(long id, Integer leave, Date startLeaveDate, Date endLeaveDate) {
		super();
		this.id = id;
		this.leave = leave;
		this.startLeaveDate = startLeaveDate;
		this.endLeaveDate = endLeaveDate;
	}
	
	@Id
	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "is_Leave")
	public Integer getLeave() {
		return leave;
	}
	
	
	public void setLeave(Integer leave) {
		this.leave = leave;
	}
	
	@Column(name = "startLeave_date")
	public Date getStartLeaveDate() {
		return startLeaveDate;
	}
	
	
	public void setStartLeaveDate(Date startLeaveDate) {
		this.startLeaveDate = startLeaveDate;
	}
	
	@Column(name = "endLeave_date")
	public Date getEndLeaveDate() {
		return startLeaveDate;
	}
	
	
	public void setEndLeaveDate(Date endLeaveDate) {
		this.endLeaveDate = endLeaveDate;
	}

	@Override
	public String toString() {
		return "Reporter [id=" + id + ", leave=" + leave + "]";
	}
	
	
	

}
