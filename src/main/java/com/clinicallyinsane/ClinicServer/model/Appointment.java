package com.clinicallyinsane.ClinicServer.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.sql.Time;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long apptID;

    @Column
    @NotNull
    private String symptoms;


    @Column(name = "appt_date")
    private String apptDate;

    @Column(name = "appt_time")
    private String apptTime;





    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "patient_id",unique = true)
    private UserProfile userProfile;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Doctor doctor;



    @Transient
    private long doctorIdTest;

    public void setDoctorId(long doctorIdTest) {
        this.doctorIdTest = doctorIdTest;
    }

    public long getDoctorId() {
        return doctorIdTest;
    }

    public Appointment() {
    }

    public long getApptID() {
        return apptID;
    }

    public void setApptID(long apptID) {
        this.apptID = apptID;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getApptDate() {
        return apptDate;
    }

    public void setApptDate(String apptDate) {
        this.apptDate = apptDate;
    }

    public String getApptTime() {
        return apptTime;
    }

    public void setApptTime(String apptTime) {
        this.apptTime = apptTime;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
