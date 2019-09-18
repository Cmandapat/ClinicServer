package com.clinicallyinsane.ClinicServer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinicallyinsane.ClinicServer.exception.ResourceNotFoundException;
import com.clinicallyinsane.ClinicServer.model.Doctor;
import com.clinicallyinsane.ClinicServer.model.Reporter;
import com.clinicallyinsane.ClinicServer.repository.ReporterRepository;

@RestController
@RequestMapping("/api/r")
public class ReporterController {
	
	@Autowired
	private ReporterRepository ReporterRepository;
	
	
		
		
		@PutMapping("/{doctorId}")
	    public ResponseEntity<Reporter> addLeave
	    (@PathVariable(value = "doctorId") Long doctorId,
	     @Valid @RequestBody Doctor doctorDetails) throws ResourceNotFoundException{

	        Reporter doctor = ReporterRepository.findById(doctorId).orElseThrow(()-> 
	        new ResourceNotFoundException("Doctor not found::" + doctorId));
	        doctor.setLeave(doctorDetails.isLeave());
	        doctor.setLeaveDate(doctorDetails.getLeaveDate());
	        final Reporter updatedDoctor = ReporterRepository.save(doctor);
	        return ResponseEntity.ok(updatedDoctor);
		}
	}

