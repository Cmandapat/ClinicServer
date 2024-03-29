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
    public List<Appointment> getAllAppointments() throws  ParseException{
        List<Appointment> appointments = apptRepository.findAll();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(int i = 0; i < appointments.size(); i++) {
            if(appointments.get(i).getDoctor().getLeave() == 1) {
                Date requestedDate = sdf.parse(appointments.get(i).getApptDate());
                Date doctorLeaveStartDate = sdf.parse(appointments.get(i).getDoctor().getLeaveStartDate());
                Date doctorLeaveEndDate = sdf.parse(appointments.get(i).getDoctor().getLeaveEndDate());
                if(doctorLeaveStartDate.compareTo(requestedDate) == 0 || doctorLeaveEndDate.compareTo(requestedDate) == 0) {
                    appointments.get(i).setDoctor(null);
                } else if(requestedDate.compareTo(doctorLeaveStartDate) > 0 && requestedDate.compareTo(doctorLeaveEndDate) < 0) {
                    appointments.get(i).setDoctor(null);
                }

            }
            apptRepository.save(appointments.get(i));
        }

        return apptRepository.findAll();
    }
//    @GetMapping("/appointment/{apptID}")
//    public ResponseEntity<Appointment> getAppointmentByID(@PathVariable (value = "apptID") Long apptID, Appointment apptDetails) throws ResourceNotFoundException {
//        Appointment appt = apptRepository.findById(apptID).orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
//
//        return ResponseEntity.ok().body(appt);
//    }

    @GetMapping("/appointment/patient/{id}")
    public ResponseEntity<Appointment> getPatientAppointment(@PathVariable(value = "id") String userId) throws ParseException{
        List<Appointment> appointments = apptRepository.findAll();
        for(Appointment app : appointments) {
            System.out.println(app);
        }
        List<Appointment> patientAppointment = new ArrayList<>();
        for(Appointment app : appointments) {
            if(app.getUserProfile().getUserID().equals(userId)) {
                patientAppointment.add(app);
            }
        }

        Appointment appointment = patientAppointment.get(0);
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date requestedDate = sdf.parse(appointment.getApptDate());
//        if(appointment.getDoctor().getLeave() == 1) {
//            Date doctorLeaveStartDate = sdf.parse(appointment.getDoctor().getLeaveStartDate());
//            Date doctorLeaveEndDate = sdf.parse(appointment.getDoctor().getLeaveEndDate());
//            if(doctorLeaveStartDate.compareTo(requestedDate) == 0 || doctorLeaveEndDate.compareTo(requestedDate) == 0) {
//                appointment.setDoctor(null);
//            } else if(requestedDate.compareTo(doctorLeaveStartDate) > 0 && requestedDate.compareTo(doctorLeaveEndDate) < 0) {
//                appointment.setDoctor(null);
//            }
//        }

        apptRepository.save(appointment);
        return ResponseEntity.ok().body(appointment);
    }

    //assuming that patient id is handled and sent, apptID auto incremented
    @PostMapping("/appointment/{id}")
    public ResponseEntity<Appointment> addAppointment(@PathVariable(name = "id") String patientId,@Valid @RequestBody Appointment appt) throws ResourceNotFoundException, ParseException {
        Doctor doctor = doctorRepository.findById(appt.getDoctorID()).orElseThrow(()->new ResourceNotFoundException("doc not found"+ appt.getDoctorID()) );
        UserProfile patient = userProfileRepository.findById(patientId).orElseThrow(()
        -> new ResourceNotFoundException("patient not found"));
        /*
        * First Lets check if doctor is on leave
        * 1 = Leave
        * 0 = Not
        * */
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date requestedDate = sdf.parse(appt.getApptDate());
        if(doctor.getLeave() == 1) {
            Date doctorLeaveStartDate = sdf.parse(doctor.getLeaveStartDate());
            Date doctorLeaveEndDate = sdf.parse(doctor.getLeaveEndDate());
            if(doctorLeaveStartDate.compareTo(requestedDate) == 0 || doctorLeaveEndDate.compareTo(requestedDate) == 0) {

                return ResponseEntity.status(400).build();
            } else if(requestedDate.compareTo(doctorLeaveStartDate) > 0 && requestedDate.compareTo(doctorLeaveEndDate) < 0) {

                return ResponseEntity.status(400).build();
            }

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
            for (DoctorSchedule ds : doctorSchedules) {
                if (ds.getDoctor().getId() == doctor.getId()) {
                    requestedDoctorSchedule.add(ds);
                }
            }
            for (DoctorSchedule ds : requestedDoctorSchedule) {
                Date dateRequested = sdf.parse(ds.getAppointmentDate());
                if (dateRequested.compareTo(requestedDate) == 0) {
                    if (ds.getAppointmentTime().equals(appt.getApptTime())) {
                        return ResponseEntity.status(400).build();
                    }
                }
            }
        }


        appt.setUserProfile(patient);
        appt.setDoctor(doctor);

        apptRepository.save(appt);
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        doctorSchedule.setDoctor(doctor);
        doctorSchedule.setAppointmentDate(appt.getApptDate());
        doctorSchedule.setAppointmentTime(appt.getApptTime());
        doctorSchedule.setAppointment(appt);
        doctorScheduleRepository.save(doctorSchedule);
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

    @DeleteMapping("/appointment/{apptID}")
    public ResponseEntity deleteAppt(@PathVariable (value = "apptID") Long apptID) throws ResourceNotFoundException {
        Appointment appt = apptRepository.findById(apptID).orElseThrow(()-> new ResourceNotFoundException("Appointment not found: " + apptID));
        Doctor d = doctorRepository.findById(appt.getDoctor().getId()).orElseThrow(()-> new ResourceNotFoundException("not found"));
        //d.removeAppointment(appt);
        apptRepository.delete(appt);
//        DoctorSchedule doctorSchedule = doctorScheduleRepository.findById(appt.getApptID()).orElseThrow(()->
//                new ResourceNotFoundException("appointment not found on this time"));
//        doctorScheduleRepository.delete(doctorSchedule);

        return ResponseEntity.ok().build();
    }


}
