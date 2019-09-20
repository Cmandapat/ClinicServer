package com.clinicallyinsane.ClinicServer.controller;

import com.clinicallyinsane.ClinicServer.exception.ResourceNotFoundException;
import com.clinicallyinsane.ClinicServer.model.Doctor;
import com.clinicallyinsane.ClinicServer.repository.AppointmentRepository;
import com.clinicallyinsane.ClinicServer.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinicallyinsane.ClinicServer.model.Appointment;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/a")
public class AppointmentController {

    @Autowired
    private AppointmentRepository apptRepository;

    @GetMapping("/appointment")
    public List<Appointment> getAllAppointments() {
        return apptRepository.findAll();
    }
    @GetMapping("/appointment/{apptID}")
    public ResponseEntity<Appointment> getAppointmentByID(@PathVariable (value = "apptID") Long apptID, Appointment apptDetails) throws ResourceNotFoundException {
        Appointment appt = apptRepository.findById(apptID).orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        return ResponseEntity.ok().body(appt);
    }



    //assuming that patient id is handled and sent, apptID auto incremented
    @PostMapping("/appointment")
    public Appointment addAppointment(@Valid @RequestBody Appointment appt) {
        return apptRepository.save(appt);
    }

    //possibly updating Appointment


    @DeleteMapping("/appointment/{apptID}")
    public ResponseEntity deleteAppt(@PathVariable (value = "apptID") Long apptID) throws ResourceNotFoundException {
        Appointment appt = apptRepository.findById(apptID).orElseThrow(()-> new ResourceNotFoundException("Appointment not found: " + apptID));
        apptRepository.delete(appt);
        return ResponseEntity.ok().build();
    }






}
