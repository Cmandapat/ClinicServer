package com.clinicallyinsane.ClinicServer.controller;


import com.clinicallyinsane.ClinicServer.exception.ResourceNotFoundException;
import com.clinicallyinsane.ClinicServer.model.DoctorSchedule;
import com.clinicallyinsane.ClinicServer.repository.DoctorScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ds/")
public class DoctorScheduleController {

    @Autowired
    private DoctorScheduleRepository doctorScheduleRepository;

    @GetMapping("/schedule/{id}")
    public ResponseEntity<DoctorSchedule> findDoctorSchedule(@PathVariable(name ="id") Long doctorId)
    throws ResourceNotFoundException {

        List<DoctorSchedule> doctorSchedules= doctorScheduleRepository.findAllById(Collections.singleton(doctorId));
        for (DoctorSchedule ds : doctorSchedules) {
            System.out.println(ds);
        }
        return ResponseEntity.ok().build();
    }
}
