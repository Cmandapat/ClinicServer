package com.clinicallyinsane.ClinicServer.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "doctor")
public class Reporter {
	
	

	private long id;
	private int leave;
	private Date leaveDate;
	
	
	public Reporter() {
		super();
	}

	public Reporter(long doctorId, int isLeave, Date leaveDate) {
		super();
		this.id = doctorId;
		this.leave = isLeave;
		this.leaveDate = leaveDate;
	}
	@Id
	public long getId() {
		return id;
	}
	
	public void setId(long doctorId) {
		this.id = doctorId;
	}
	
	@Column(name = "leave")
	public int  getLeave() {
		return leave;
	}
	public void setLeave(int isLeave) {
		this.leave = isLeave;
	}
	@Column(name = "leaveDate")
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date date) {
		this.leaveDate = date;
	}
	

}
