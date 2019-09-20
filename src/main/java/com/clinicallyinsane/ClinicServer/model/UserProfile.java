package com.clinicallyinsane.ClinicServer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_profile")
public class UserProfile {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name ="uuid",strategy = "org.hibernate.id.UUIDGenerator" )
	@Column(name ="id")
	private String userID;
	@Column(name = "first_name")
	@Size(max = 100)
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column
	@JsonFormat(pattern="MM-dd-yyyy")
	private Date dob;

	@Column
	@Size(max = 6)
	private String gender;

	@Column(name = "present_address")
	@Size(max = 100)
	private String presentAddress;
	@Column(name = "permanent_address")
	@Size(max = 100)
	private String permanentAddress;

	@Column(name = "phone")
	private int phoneNumber;
	@Column(name = "email")
	@Size(max = 100)
	private String emailId;

	@OneToOne(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,mappedBy = "userProfile")
	private UserCredentials userCredentials;



	@OneToOne(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,mappedBy = "userProfile")
	private Appointment appointment;

	
	public UserProfile() {}

	public UserProfile(String userID, String firstName, String lastName, Date dob, String gender, String presentAddress, String permanentAddress, int phoneNumber, String emailId) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.presentAddress = presentAddress;
		this.permanentAddress = permanentAddress;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
	}

	public String getUserID() {
		return userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getDob() {
		return dob;
	}

	public String getGender() {
		return gender;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "UserProfile{" +
				"userID='" + userID + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", dob=" + dob +
				", gender='" + gender + '\'' +
				", presentAddress='" + presentAddress + '\'' +
				", permanentAddress='" + permanentAddress + '\'' +
				", phoneNumber=" + phoneNumber +
				", emailId='" + emailId + '\'' +
				'}';
	}
}
