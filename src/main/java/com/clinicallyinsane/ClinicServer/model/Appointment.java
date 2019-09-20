package com.clinicallyinsane.ClinicServer.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.sql.Time;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long apptID;

//    @Column(name = "patient_id")
//    private String patientID;

    @Column
    private String symptoms;
    @Column(name = "appt_date")
    private Date apptDate;
    @Column(name = "appt_time")
    private Time apptTime;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Doctor doctor;

    public Appointment() {
    }

    public long getApptID() {
        return apptID;
    }

    public void setApptID(long apptID) {
        this.apptID = apptID;
    }

//    public String getPatientID() {
//        return patientID;
//    }
//
//    public void setPatientID(String patientID) {
//        this.patientID = patientID;
//    }


    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public Date getApptDate() {
        return apptDate;
    }

    public void setApptDate(Date apptDate) {
        this.apptDate = apptDate;
    }

    public Time getApptTime() {
        return apptTime;
    }

    public void setApptTime(Time apptTime) {
        this.apptTime = apptTime;
    }

}
