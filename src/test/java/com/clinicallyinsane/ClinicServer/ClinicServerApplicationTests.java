package com.clinicallyinsane.ClinicServer;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.clinicallyinsane.ClinicServer.controller.AppointmentController;
import com.clinicallyinsane.ClinicServer.controller.DoctorController;
import com.clinicallyinsane.ClinicServer.controller.DoctorScheduleController;
import com.clinicallyinsane.ClinicServer.controller.UserCredentialsController;
import com.clinicallyinsane.ClinicServer.controller.UserProfileController;
import com.clinicallyinsane.ClinicServer.exception.ResourceNotFoundException;
import com.clinicallyinsane.ClinicServer.model.Appointment;
import com.clinicallyinsane.ClinicServer.model.Doctor;
import com.clinicallyinsane.ClinicServer.model.UserCredentials;
import com.clinicallyinsane.ClinicServer.model.UserProfile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClinicServerApplicationTests {

	@Autowired
	private DoctorController dc;

	@Autowired
	private AppointmentController ac;

	@Autowired
	private DoctorScheduleController dsc;

	@Autowired
	private UserProfileController uc;

	@Autowired
	private UserCredentialsController ucc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getDoctorTest() {
		assertEquals(2, dc.getAllDoctors().size());
	}

	@Test
	public void getDoctorByIdTest() {
		long id = 14;
		try {
			assertEquals(14, dc.getDoctorById(id).getBody().getId());
		} catch (ResourceNotFoundException e) {

			System.out.println("getDoctorByIdTest: Error");
		}
	}

	@Test
	public void getDoctorsByTypeTest() {
		assertEquals(1, dc.getAllDoctorsRequestedType("Surgery").size());

	}

	@Test
	public void addDoctorTest() {
		Doctor d = new Doctor();
		d.setFirstName("Test");
		d.setLastName("Test");
		d.setSpecialization("Test");
		d.setYearsOfExperience(3);
		assertEquals("Test", dc.addDoctor(d).getFirstName());
	}

	// New Test
	@Test
	public void updateDoctorTest() {
		long id = 17;
		Doctor d = new Doctor();
		d.setSpecialization("Test1");
		d.setYearsOfExperience(5);
		try {
			assertEquals(17, dc.updateDoctor(id, d).getBody().getId());
		} catch (ResourceNotFoundException e) {

			System.out.println("updateDoctorTest: Error");
		}
	}

	@Test
	public void updateDoctorLeaveTest() {
		long id = 16;
		Doctor d = new Doctor();
		d.setLeave(1);
		d.setLeaveStartDate("2019-09-25");
		d.setLeaveEndDate("2019-09-26");
		try {
			assertEquals(16, dc.updateDoctorLeave(id, d).getBody().getId());
		} catch (ResourceNotFoundException e) {

			System.out.println("updateDoctorLeaveTest: Error");
		}
	}

	
	@Test public void deleteDoctorTest() { 
		long id = 21; 
		try 
		{
			assertEquals("<200 OK OK,[]>",dc.deleteDoctor(id).toString()); 
		} 
		catch (ResourceNotFoundException e) {
			System.out.println("deleteDoctorTest: Error"); 
		}
	}
	

	@Test
	public void getAppointmentsTest() {
		try {
			assertEquals(0, ac.getAllAppointments().size());
		} catch (ParseException e) {
			System.out.println("getAppointmentsTest: Error");
		}
	}

	@Test
	public void addAppointmentTest() {
		Appointment a = new Appointment();
		a.setApptDate("2019-02-25");
		a.setApptTime("9:45");
		a.setSymptoms("Sick");
		a.setDoctorID(14);
		try {
			assertEquals("<200 OK OK,[]>", ac.addAppointment("27e8b26", a).getBody().getApptID());
		} catch (ResourceNotFoundException e) {
			System.out.println("addAppointmentTest: Error");
		} catch (ParseException e) {
			System.out.println("addAppointmentTest: Error");
		}
	}

	@Test 
	public void getAppointmentByUserIdTest() 
	{ 
		try {
			assertEquals(45, ac.getPatientAppointment("9589a2d").getBody().getApptID());
		} catch (ParseException e) {
			System.out.println("getAppointmentByUserIdTest: Error");
		} 
		
		
	}

	@Test
	public void removedDoctorFromAppointment() {
		long id = 45;
		try {
			assertEquals("<200 OK OK,[]>",ac.removeDoctoronAppointment(id).toString());
		} catch (ResourceNotFoundException e) {
			System.out.println("removedDoctorFromAppointment: Error");
		}
	}

	@Test
	public void deleteApptTest() {
		long id = 48;
		try {
			assertEquals("<200 OK OK,[]>",ac.deleteAppt(id).toString());
		} catch (ResourceNotFoundException e) {
			System.out.println("delteApptTest: Error");
		}
	}

	@Test
	public void findDoctorScheduled() {
		long id = 14;
		try {
			assertEquals("<200 OK OK,[]>",dsc.findDoctorSchedule(id).toString());
		} catch (ResourceNotFoundException e) {
			System.out.println("findDoctorScheduled: Error");
		}
	}

	@Test
	public void getAllUserProfilesTest() {
		assertEquals(3,uc.getAllUserProfiles().size());
	}

	@Test
	public void getUserProfileByIdTest() {
		try {
			assertEquals("5bb1a86",uc.getUserProfileById("5bb1a86").getBody().getUserID());
		} catch (ResourceNotFoundException e) {
			System.out.println("getUserProfileByIdTest: Error");
		}
	}

	@Test
	public void addUserProfileTest() {
		UserProfile u = new UserProfile();
		u.setFirstName("Test");
		u.setLastName("Test");
		u.setPassword("test");
		u.setGender("T");
		u.setDob("2019-09-23");
		u.setPresentAddress("Test");
		u.setPermanentAddress("Test");
		u.setPhoneNumber(123654789);
		u.setEmailId("test@mail.com");
		assertEquals("PAT",uc.addUserProfile(u).getBody().getUserType());
	}

	@Test
	public void updateUserProfileTest() {
		UserProfile u = new UserProfile();
		u.setPermanentAddress("Test");
		u.setPresentAddress("Test");
		u.setPhoneNumber(123456789);
		try {
			assertEquals("5bb1a86",uc.updateUserProfile("5bb1a86", u).getBody().getUserID());
		} catch (ResourceNotFoundException e) {
			System.out.println("updateUserProfileTest: Error");
		}
	}

	@Test
	public void deleteUserProfileTest() {
		try {
			assertEquals("<200 OK OK,[]>",uc.deleteUserProfile("01bafaf").toString());
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getUserCredentialsListTest() {
		assertEquals(3,ucc.userCredentialsList().size());
	}

	@Test
	public void getUserCredentialsByIdTest() {
		try {
			assertEquals("5bb1a86",ucc.getUserById("5bb1a86").getBody().getId());
		} catch (ResourceNotFoundException e) {
			System.out.println("getUserCredentialsByIdTest: Error");
		}
	}

	@Test
	public void updateLoginTest() {
		try {
			UserCredentials uc = ucc.getUserById("5bb1a86").getBody();
			assertEquals("5bb1a86",ucc.updateLogin(uc).getBody().getId());
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void updateLogoutTest() {
		try {
			assertEquals("5bb1a86",ucc.updateLogout("5bb1a86").getBody().getId());
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
