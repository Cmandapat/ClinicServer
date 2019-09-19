package com.clinicallyinsane.ClinicServer.model;

import javax.persistence.*;
import java.util.Date;


//make sure autoincrement is fixed

@Entity
@Table(name = "appointment")
public class Appointment {
    private long apptID;
    private long patientID;
    private long doctorID;
    private String symptoms;
    private Date apptDate;

    public Appointment() {}

    //not sure if id is included since its autogenerated(if it is autogenerated)
    public Appointment(long apptID, long patientID, long doctorID, String symptoms, Date apptDate) {
        this.apptID = apptID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.symptoms = symptoms;
        this.apptDate = apptDate;
    }


    //look out for the Auto

   //
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    public long getApptID() {
        return apptID;
    }

    public void setApptID(long apptID) {
        this.apptID = apptID;
    }

    @Column
    public long getPatientID() {
        return patientID;
    }

    public void setPatientID(long patientID) {
        this.patientID = patientID;
    }

    @Column
    public long getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(long doctorID) {
        this.doctorID = doctorID;
    }

    @Column
    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    @Column
    public Date getApptDate() {
        return apptDate;
    }

    public void setApptDate(Date apptDate) {
        this.apptDate = apptDate;
    }



}
