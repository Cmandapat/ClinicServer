package com.clinicallyinsane.ClinicServer.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.clinicallyinsane.ClinicServer.model.Admin;;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

}
