package com.clinicallyinsane.ClinicServer.controller;

import com.clinicallyinsane.ClinicServer.exception.ResourceNotFoundException;
import com.clinicallyinsane.ClinicServer.model.Admin;
import com.clinicallyinsane.ClinicServer.model.Appointment;
import com.clinicallyinsane.ClinicServer.repository.AdminRepository;
import com.clinicallyinsane.ClinicServer.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.clinicallyinsane.ClinicServer.model.Doctor;
import com.clinicallyinsane.ClinicServer.repository.DoctorRepository;


import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ad")
public class AdminController {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@GetMapping("/admin")
	public List<Doctor> getAllDoctors(){
		
		return doctorRepository.findAll();
	}
	
	@GetMapping("/admin/appt")
	public List<Appointment> getAllAppointements(){
		return appointmentRepository.findAll();
	}
	
	 @GetMapping("/admin/{id}")
	    public ResponseEntity<Doctor> getDoctorById(@PathVariable(value = "id") Long doctorId, @Valid Doctor doctorDetails) throws
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
