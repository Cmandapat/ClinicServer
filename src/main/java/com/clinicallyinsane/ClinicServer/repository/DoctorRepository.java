package com.clinicallyinsane.ClinicServer.repository;

import com.clinicallyinsane.ClinicServer.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
