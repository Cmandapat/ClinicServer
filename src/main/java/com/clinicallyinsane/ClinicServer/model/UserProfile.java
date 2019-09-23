package com.clinicallyinsane.ClinicServer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_profile")
public class UserProfile {

	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name ="uuid",strategy = "uuid" )
	@Column(name ="id")
	private String userID;

	@PrePersist
	public void createUserId() {
		if(userID == null) {
			userID = UUID.randomUUID().toString().substring(0,7);
		} else {
			userID = userID.substring(0, 7);
		}
	}

	@NotNull
	@Column(name = "first_name")
	@Size(max = 100)
	private String firstName;
	@NotNull
	@Column(name = "last_name")
	private String lastName;
	@NotNull
	@Column
	@JsonFormat(pattern="MM-dd-yyyy")
	private Date dob;

	@NotNull
	@Column
	@Size(max = 6)
	private String gender;

	@NotNull
	@Column(name = "present_address")
	@Size(max = 100)
	private String presentAddress;

	@NotNull
	@Column(name = "permanent_address")
	@Size(max = 100)
	private String permanentAddress;

	@NotNull
	@Column(name = "phone")
	private int phoneNumber;
	@NotNull
	@Column(name = "email")
	@Size(max = 100)
	private String emailId;

	@OneToOne(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,mappedBy = "userProfile")
	private UserCredentials userCredentials;

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,mappedBy = "userProfile")
	private Appointment appointment;

	@Transient
	private String password;

	@Transient
	private int code;

	public int getCode() {
		return code;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
