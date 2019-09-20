package com.clinicallyinsane.ClinicServer.controller;

import com.clinicallyinsane.ClinicServer.exception.*;
import com.clinicallyinsane.ClinicServer.model.Doctor;
import com.clinicallyinsane.ClinicServer.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/d")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    //url should look like localhost:xxxx/api/d/doctors
    /****
     * Function returns a list of all Doctors
     * findAll() comes the JpaRepoistory Class
     * */
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    /**
     * @Params long doctorId
     * read given Id to display a certain doctor
     * returns doctor to the html body,
     * can also return a status code via: ResponseEntity.ok().build() --> returns a status 200 code;
     * */
    @GetMapping("/doctors/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable(value = "id") Long id) throws
            ResourceNotFoundException {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Doctor not found for this id:" + id));

        return ResponseEntity.ok().body(doctor);
    }


    /*
    * @RequestBody takes input from FrontEnd into a doctor object
    * */
    @PostMapping("/doctors")
    public Doctor addDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable(value = "id") Long doctorId,
                                               @Valid @RequestBody Doctor doctorDetails) throws ResourceNotFoundException{

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor not found::" + doctorId));
        doctor.setYearsOfExperience(doctorDetails.getYearsOfExperience());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        final Doctor updatedDoctor = doctorRepository.save(doctor);
        return ResponseEntity.ok(updatedDoctor);

    }

    @PutMapping("/doctors/reporter/{id}")
    public ResponseEntity<Doctor> updateDoctorLeave(@PathVariable(value = "id") Long doctorId,
                                                 @Valid @RequestBody Doctor doctorDetails) throws ResourceNotFoundException{

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor not found::" + doctorId));
        doctor.setLeave(doctorDetails.getLeave());
        doctor.setLeaveStartDate(doctorDetails.getLeaveStartDate());
        doctor.setLeaveEndDate(doctorDetails.getLeaveEndDate());
        final Doctor updatedDoctor = doctorRepository.save(doctor);
        return ResponseEntity.ok(updatedDoctor);

    }

    @SuppressWarnings("rawtypes")
	@DeleteMapping("/doctors/{id}")
    public ResponseEntity deleteDoctor(@PathVariable(value ="id") Long doctorId) throws ResourceNotFoundException {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor Not found:" + doctorId));
        doctorRepository.delete(doctor);
        return ResponseEntity.ok().build();
    }
    
    


}
