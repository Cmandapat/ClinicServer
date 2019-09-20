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
    private ReporterRepository reporterRepository;
	@PutMapping("/doctors/{id}")
    public ResponseEntity<Reporter> updateDoctor(@PathVariable(value = "id") Long doctorId,
                                               @Valid @RequestBody Doctor doctorDetails) throws ResourceNotFoundException{

        Reporter reporter = reporterRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor not found::" + doctorId));
        reporter.setLeave(doctorDetails.getLeave());
        reporter.setStartLeaveDate(doctorDetails.getStartLeaveDate());
        reporter.setEndLeaveDate(doctorDetails.getEndLeaveDate());
        final Reporter updatedDoctor = reporterRepository.save(reporter);
        return ResponseEntity.ok(updatedDoctor);

    }

}
 