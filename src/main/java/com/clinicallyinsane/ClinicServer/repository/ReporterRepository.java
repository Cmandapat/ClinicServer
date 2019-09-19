package com.clinicallyinsane.ClinicServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinicallyinsane.ClinicServer.model.Reporter;

@Repository
public interface ReporterRepository extends JpaRepository<Reporter, Long>{

}
