package com.clinicallyinsane.ClinicServer.repository;

import org.springframework.stereotype.Repository;
import com.clinicallyinsane.ClinicServer.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{
}
