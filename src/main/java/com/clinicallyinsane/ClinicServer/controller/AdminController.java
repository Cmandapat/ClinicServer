package com.clinicallyinsane.ClinicServer.controller;

import com.clinicallyinsane.ClinicServer.exception.ResourceNotFoundException;
import com.clinicallyinsane.ClinicServer.model.Admin;
import com.clinicallyinsane.ClinicServer.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.clinicallyinsane.ClinicServer.model.Doctor;
import com.clinicallyinsane.ClinicServer.repository.DoctorRepository;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ad")
public class AdminController {

	//@Autowired
//	private AdminRepository adminRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	
	@GetMapping("/admin")
	public List<Doctor> getAllDoctors(){
		
		return doctorRepository.findAll();
	}
//	public List<Admin> getAllAdmins(){
//		
//		return adminRepository.findAll();
//	}
	/*public List<Appointments> getAllAppointements(){
		retrun appointmentRepository.findAll();
	}*/
	
	 @GetMapping("/admin/{id}")
	    public ResponseEntity<Doctor> getDoctorById(@PathVariable(value = "id") Long doctorId, @Valid @RequestBody Doctor doctorDetails) throws
	            ResourceNotFoundException {
		 
	        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor not found for this id:" + doctorId));

	        return ResponseEntity.ok().body(doctor);
	    }

	 
	 @PostMapping("/admin/doctors")
	    public Doctor addDoctor(@Valid @RequestBody Doctor doctor) {
	        return doctorRepository.save(doctor);
	    }

	    @PutMapping("/admin/doctors/{id}")
	    public ResponseEntity<Doctor> updateDoctor(@PathVariable(value = "id") Long doctorId,
	                                               @Valid @RequestBody Doctor doctorDetails) throws ResourceNotFoundException{

	        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor not found::" + doctorId));
	        doctor.setYearsOfExperience(doctorDetails.getYearsOfExperience());
	        doctor.setSpecialization(doctorDetails.getSpecialization());
	        final Doctor updatedDoctor = doctorRepository.save(doctor);
	        return ResponseEntity.ok(updatedDoctor);

	    }

	    @DeleteMapping("/admin/doctors/{id}")
	    public ResponseEntity<Doctor> deleteDoctor(@PathVariable(value ="id") Long doctorId) throws ResourceNotFoundException {
	        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor Not found:" + doctorId));
	        doctorRepository.delete(doctor);
	        return ResponseEntity.ok().build();
	    }
	
	
	
	
	
}
