package com.clinicallyinsane.ClinicServer.repository;

import com.clinicallyinsane.ClinicServer.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, String> {
}
