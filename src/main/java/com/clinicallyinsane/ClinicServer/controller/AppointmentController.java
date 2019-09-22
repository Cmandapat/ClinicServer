package com.clinicallyinsane.ClinicServer.controller;

import com.clinicallyinsane.ClinicServer.exception.ResourceNotFoundException;
import com.clinicallyinsane.ClinicServer.model.Doctor;
import com.clinicallyinsane.ClinicServer.model.DoctorSchedule;
import com.clinicallyinsane.ClinicServer.model.UserProfile;
import com.clinicallyinsane.ClinicServer.repository.AppointmentRepository;
import com.clinicallyinsane.ClinicServer.repository.DoctorRepository;
import com.clinicallyinsane.ClinicServer.repository.DoctorScheduleRepository;
import com.clinicallyinsane.ClinicServer.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinicallyinsane.ClinicServer.model.Appointment;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/a")
public class AppointmentController {

    @Autowired
    private AppointmentRepository apptRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private DoctorScheduleRepository doctorScheduleRepository;

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
    @PostMapping("/appointment/{id}")
    public ResponseEntity<Appointment> addAppointment(@PathVariable(name = "id") String patientId,@Valid @RequestBody Appointment appt) throws ResourceNotFoundException, ParseException {
        Doctor doctor = doctorRepository.findById(appt.getDoctorId()).orElseThrow(()->new ResourceNotFoundException("doc not found"+ appt.getDoctorId()) );
        UserProfile patient = userProfileRepository.findById(patientId).orElseThrow(()
        -> new ResourceNotFoundException("patient not found"));
        /*
        * First Lets check if doctor is on leave
        * 1 = Leave
        * 0 = Not
        * */
        System.out.println(appt.getApptTime());
        System.out.println(appt.getApptDate());
        DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date requestedDate = sdf.parse(appt.getApptDate());
//        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
//        Date requestedTime = timeFormat.parse(requestedStringArrayDate[1]);
        if(doctor.getLeave() == 1) {
            Date doctorLeaveStartDate = sdf.parse(doctor.getLeaveStartDate());
            Date doctorLeaveEndDate = sdf.parse(doctor.getLeaveEndDate());
            System.out.println(requestedDate + "in test");
            System.out.println(doctorLeaveStartDate);
            System.out.println(doctorLeaveEndDate);

            if(doctorLeaveStartDate.compareTo(requestedDate) == 0 || doctorLeaveEndDate.compareTo(requestedDate) == 0) {
                System.out.println("test");
                return ResponseEntity.status(400).build();
            } else if(requestedDate.compareTo(doctorLeaveStartDate) > 0 && requestedDate.compareTo(doctorLeaveEndDate) < 0) {
                System.out.println("test2");
                return ResponseEntity.status(400).build();
            }

            List<DoctorSchedule> doctorSchedules = doctorScheduleRepository.findAll();
            //if its empty? lets have our first doctor in there.
            if(doctorSchedules.isEmpty() || doctorSchedules == null) {
                DoctorSchedule doctorSchedule = new DoctorSchedule();
                doctorSchedule.setDoctor(doctor);
                doctorSchedule.setAppointmentDate(appt.getApptDate());
                doctorSchedule.setAppointmentTime(appt.getApptTime());
                doctorScheduleRepository.save(doctorSchedule);
            } else {
                //now check if doctor is ready for appointment
                List<DoctorSchedule> requestedDoctorSchedule = new ArrayList<DoctorSchedule>();
                for(DoctorSchedule ds : doctorSchedules) {
                    if(ds.getDoctor().getId() == doctor.getId()) {
                        requestedDoctorSchedule.add(ds);
                    }
                 }
                for(DoctorSchedule ds  : requestedDoctorSchedule) {
                    Date dateRequested = sdf.parse(ds.getAppointmentDate());
                    if(dateRequested.compareTo(requestedDate) == 0) {
                        if(ds.getAppointmentTime().equals(appt.getApptTime())) {
                            return ResponseEntity.status(400).build();
                        }
                    }
                }
            }




        }
            DoctorSchedule doctorSchedule = new DoctorSchedule();
            doctorSchedule.setDoctor(doctor);
            doctorSchedule.setAppointmentDate(appt.getApptDate());
            doctorSchedule.setAppointmentTime(appt.getApptTime());
            doctorScheduleRepository.save(doctorSchedule);
            appt.setUserProfile(patient);
            appt.setDoctor(doctor);
//            appt.setApptTime(appt.getApptTime());
//            appt.setApptDate(appt.getApptDate());
//            appt.setSymptoms(appt.getSymptoms());
            apptRepository.save(appt);
            return ResponseEntity.ok().body(appt);


    }


    @PutMapping("/appointment/doctor/{apptId}")
    public ResponseEntity<Appointment> removeDoctoronAppointment(@PathVariable (value = "apptId") Long apptId) throws ResourceNotFoundException {
        Appointment appt = apptRepository.findById(apptId).orElseThrow(()->
                new ResourceNotFoundException("Appointment not found"));

        appt.setDoctor(null);
        Appointment updatedAppointment = apptRepository.save(appt);
        return ResponseEntity.ok().build();
    }
    //possibly updating Appointment


    @DeleteMapping("/appointment/{apptID}")
    public ResponseEntity deleteAppt(@PathVariable (value = "apptID") Long apptID) throws ResourceNotFoundException {
        Appointment appt = apptRepository.findById(apptID).orElseThrow(()-> new ResourceNotFoundException("Appointment not found: " + apptID));
        apptRepository.delete(appt);
        return ResponseEntity.ok().build();
    }








}
