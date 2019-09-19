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
	private int leave;
	private Date leaveDate;
	
	public Reporter() {
		super();
		
	}

	public Reporter(long id, int leave, Date leaveDate) {
		super();
		this.id = id;
		this.leave = leave;
		this.leaveDate = leaveDate;
	}
	
	@Id
	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "is_Leave")
	public int getLeave() {
		return leave;
	}
	
	
	public void setLeave(int leave) {
		this.leave = leave;
	}
	
	@Column(name = "leave_date")
	public Date getLeaveDate() {
		return leaveDate;
	}
	
	
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	@Override
	public String toString() {
		return "Reporter [id=" + id + ", leave=" + leave + "]";
	}
	
	
	

}
