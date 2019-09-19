package com.clinicallyinsane.ClinicServer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
	
	@Id
	@Column(name = "userID")
	private Integer userID;
	private String name;
	private Date date;
	private String gender;
	private String presentAddress;
	private String permanentAddress;
	private Integer phoneNumber;
	private String emailId;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	
	
		
}
