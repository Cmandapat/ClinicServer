package com.clinicallyinsane.ClinicServer.model;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doctor")
public class Doctor {
    private long id;
    private String name;
    private String specialization;
    private double yearsOfExperience;

    private Integer leave;
    private Date startLeaveDate;
    private Date endLeaveDate;

    public Doctor() {}

    public Doctor(long id, String name,String specialization ,double yearsOfExperience, Integer leave, Date startLeaveDate, Date endLeaveDate) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.leave = leave;
        this.startLeaveDate = startLeaveDate;
        this.endLeaveDate = endLeaveDate;
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
    public Integer getLeave() {
        return leave;
    }

    public void setLeave(Integer leave) {
        this.leave = leave;
    }

    @Column(name = "startLeave_date")
    public Date getStartLeaveDate() {
        return startLeaveDate;
    }

    public void setStartLeaveDate(Date startLeaveDate) {
        this.startLeaveDate = startLeaveDate;
    }
    
    @Column(name = "endLeave_date")
    public Date getEndLeaveDate() {
        return endLeaveDate;
    }

    public void setEndLeaveDate(Date startEndLeaveDate) {
        this.endLeaveDate = endLeaveDate;
    }
}
