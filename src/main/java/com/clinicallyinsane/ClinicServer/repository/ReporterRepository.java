package com.clinicallyinsane.ClinicServer.repository;

import com.clinicallyinsane.ClinicServer.model.Reporter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporterRepository extends JpaRepository<Reporter,Long> {
}