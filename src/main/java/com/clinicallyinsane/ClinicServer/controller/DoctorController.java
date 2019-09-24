package com.clinicallyinsane.ClinicServer.controller;

import com.clinicallyinsane.ClinicServer.exception.*;
import com.clinicallyinsane.ClinicServer.model.Doctor;
import com.clinicallyinsane.ClinicServer.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/d")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    //url should look like localhost:xxxx/api/d/doctors

    /**
     *
     * @return list of all doctors
     */
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    /**
     *
     * @param id used to find a specific doctor
     * @return a doctor specified by id
     * @throws ResourceNotFoundException
     */
    @GetMapping("/doctors/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable(value = "id") Long id) throws
            ResourceNotFoundException {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Doctor not found for this id:" + id));

        return ResponseEntity.ok().body(doctor);
    }

    /**
     * Basically our suggestDoctor() method
     * Specify what doctor you were looking and pass into speciailization
     * @param specialization ->
     * @return a list of doctors based on that specialization;
     */
    @GetMapping("/doctors/type/{type}")
    public List<Doctor> getAllDoctorsRequestedType(@PathVariable(name = "type")String specialization) {
        List<Doctor> doctors = doctorRepository.findAll();
        List<Doctor> requestedDoctor = new ArrayList<Doctor>();
        for(Doctor doc : doctors) {
            if(doc.getSpecialization().equals(specialization)) {
                requestedDoctor.add(doc);
            }
        }
        return requestedDoctor;

    }


    /**
     *
     * @param doctor --> Doctor object used to create a new Doctor
     * @return --> save doctor into database and return a 200 status code
     */
    @PostMapping("/doctors")
    public Doctor addDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    /**
     *
     * @param doctorId ->used to find doctor
     * @param doctorDetails -> updated new years of experience and change specialization
     * @return an updated doctor
     * @throws ResourceNotFoundException
     */
    @PutMapping("/doctors/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable(value = "id") Long doctorId,
                                               @Valid @RequestBody Doctor doctorDetails) throws ResourceNotFoundException{

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor not found::" + doctorId));
        doctor.setYearsOfExperience(doctorDetails.getYearsOfExperience());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        final Doctor updatedDoctor = doctorRepository.save(doctor);
        return ResponseEntity.ok(updatedDoctor);

    }

    /**
     *
     * @param doctorId
     * @param doctorDetails
     * @return
     * @throws ResourceNotFoundException
     */
    @PutMapping("/doctors/reporter/{id}")
    public ResponseEntity<Doctor> updateDoctorLeave(@PathVariable(value = "id") Long doctorId,
                                                  @RequestBody Doctor doctorDetails) throws ResourceNotFoundException{

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor not found::" + doctorId));
        doctor.setLeave(doctorDetails.getLeave());
        doctor.setLeaveStartDate(doctorDetails.getLeaveStartDate());
        doctor.setLeaveEndDate(doctorDetails.getLeaveEndDate());
        final Doctor updatedDoctor = doctorRepository.save(doctor);
        return ResponseEntity.ok(updatedDoctor);

    }

    /**
     *
     * @param doctorId
     * @param doctorDetails
     * @return
     * @throws ResourceNotFoundException
     */
    @PutMapping("/doctors/reporter/return/{id}")
    public ResponseEntity<Doctor> updateDoctorReturn(@PathVariable(value = "id") Long doctorId,
                                                    @RequestBody Doctor doctorDetails) throws ResourceNotFoundException{

        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new ResourceNotFoundException("Doctor not found::" + doctorId));
        doctor.setLeave(1);
        doctor.setLeaveStartDate(null);
        doctor.setLeaveEndDate(null);
        final Doctor updatedDoctor = doctorRepository.save(doctor);
        return ResponseEntity.ok(updatedDoctor);

    }

    /**
     *
     * @param doctorId
     * @return
     * @throws ResourceNotFoundException
     */
    @SuppressWarnings("rawtypes")
	@DeleteMapping("/doctors/{id}")
    public ResponseEntity deleteDoctor(@PathVariable(value ="id") Long doctorId) throws ResourceNotFoundException {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor Not found:" + doctorId));
        doctorRepository.delete(doctor);
        return ResponseEntity.ok().build();
    }
    
    


}
