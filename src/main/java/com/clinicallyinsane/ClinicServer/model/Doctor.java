package com.clinicallyinsane.ClinicServer.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doctor")
public class Doctor {
    private long id;
    private String name;
    private String specialization;
    private double yearsOfExperience;

    private int leave;
    private Date leaveDate;

    public Doctor() {}

    public Doctor(long id, String name,String specialization ,double yearsOfExperience, int leave, Date leaveDate) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.leave = leave;
        this.leaveDate = leaveDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column
    public String getName() {
        return name;
    }
    @Column
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name ="years_of_experience")
    public double getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(double yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
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
}
