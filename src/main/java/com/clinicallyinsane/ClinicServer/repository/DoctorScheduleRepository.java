package com.clinicallyinsane.ClinicServer.repository;

import com.clinicallyinsane.ClinicServer.model.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule,Long> {
}
