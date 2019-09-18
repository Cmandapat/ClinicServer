package com.clinicallyinsane.ClinicServer.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "UserProfile")
public class UserProfile {
	private int userID;
	private String name;
	private Date date;
	private String gender, presentAddress, permanentAddress;
	private int phoneNumber;
	private String emailId;
	
	public UserProfile(int userID, String name, Date date, String gender, String presentAddress,
			String permanentAddress, int phoneNumber, String emailId) {
		super();
		this.userID = userID;
		this.name = name;
		this.date = date;
		this.gender = gender;
		this.presentAddress = presentAddress;
		this.permanentAddress = permanentAddress;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Column	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column
	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	@Column
	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	@Column
	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "UserProfile [userID=" + userID + ", name=" + name + ", date=" + date + ", gender=" + gender
				+ ", presentAddress=" + presentAddress + ", permanentAddress=" + permanentAddress + ", phoneNumber="
				+ phoneNumber + ", emailId=" + emailId + "]";
	}
	
	
}
