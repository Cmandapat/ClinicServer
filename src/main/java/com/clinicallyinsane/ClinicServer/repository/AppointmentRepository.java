package com.clinicallyinsane.ClinicServer.repository;

import com.clinicallyinsane.ClinicServer.model.Appointment;
import com.clinicallyinsane.ClinicServer.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
