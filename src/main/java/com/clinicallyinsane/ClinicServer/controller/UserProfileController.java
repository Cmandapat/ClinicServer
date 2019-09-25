package com.clinicallyinsane.ClinicServer.controller;

import java.util.List;

import javax.validation.Valid;

import com.clinicallyinsane.ClinicServer.model.UserCredentials;
import com.clinicallyinsane.ClinicServer.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clinicallyinsane.ClinicServer.exception.ResourceNotFoundException;
import com.clinicallyinsane.ClinicServer.model.UserProfile;
import com.clinicallyinsane.ClinicServer.repository.UserProfileRepository;


/*
All urls calling this controller should have the format localhost:xxxx/api/up/*
* being any mapping required for following functions
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/up")
public class UserProfileController {
	
	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private UserCredentialsRepository userCredentialsRepository;
	
	/*
	Function is for returning all User Profiles from database
	The repository class gives us findAll() which allows grabbing the list
	 */
	@GetMapping("/UserProfiles")
	public List<UserProfile> getAllUserProfiles(){
		return userProfileRepository.findAll();
	}
	
	
	/*
	Function returns a User Profile with a specific id 
	Param id gets passed via the url path through to user id
	If the resource isn't found, we throw the custom exception defined in the exception package
	Repository interface gives the function find by id which the user id is passed to
	ResponseEntity is returned with the User Profile we received
	 */
	@GetMapping("/UserProfiles/{id}")
	public ResponseEntity<UserProfile> getUserProfileById(@PathVariable(value = "id") String userId,
														   UserProfile userProfileDetails)
													      throws ResourceNotFoundException{
		
		UserProfile userProfile = userProfileRepository.findById(userId)
								  .orElseThrow(()-> new ResourceNotFoundException("UserProfile not found with id:" + userId));

		return ResponseEntity.ok().body(userProfile);
	}
	
	
	/*
	Function returns the userProfile entered and sends to the database to be saved
	User Profile Repository interface provides the save method
	 */
	@PostMapping("/UserProfiles")
	public ResponseEntity<UserCredentials> addUserProfile(@Valid @RequestBody UserProfile userProfile) {
		userProfileRepository.save(userProfile);
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setId(userProfile.getUserID());
		userCredentials.setUserProfile(userProfile);
		userCredentials.setPassword(userProfile.getPassword());
		String acctType = userCredentials.accountTypeChecker(userProfile.getCode());
		userCredentials.setUserType(acctType);
		userCredentialsRepository.save(userCredentials);
		return  ResponseEntity.ok().body(userCredentials);
	}
	
	/*
	Function allows for a user to update their permanent address, phone number, and present address
	The user's id is passed through the url path, and the new user is passed through the method
	
	 */
	@PutMapping("/UserProfiles/{id}")
	public ResponseEntity<UserProfile> updateUserProfile(@PathVariable(value = "id") String userId,
			  											 @RequestBody UserProfile userProfileDetails)
			  											 throws ResourceNotFoundException{
		UserProfile userProfile = userProfileRepository.findById(userId)
				  .orElseThrow(()-> new ResourceNotFoundException("UserProfile not found with id:" + userId));
		userProfile.setPermanentAddress(userProfileDetails.getPermanentAddress());
		userProfile.setPhoneNumber(userProfileDetails.getPhoneNumber());
		userProfile.setPresentAddress(userProfileDetails.getPresentAddress());
		final UserProfile updatedUserProfile = userProfileRepository.save(userProfile);
		return ResponseEntity.ok(updatedUserProfile);
	}
	
	/*
	Function adds delete functionality to the controller
	User id is passed through the url path, which is found with the repository's method find by id
	Then, the repository's method delete allows us to pass the found user profile through the method
	 */
	@DeleteMapping("/UserProfiles/{id}")
	public ResponseEntity deleteUserProfile(@PathVariable(value = "id") String userId) throws ResourceNotFoundException{
		UserProfile userProfile = userProfileRepository.findById(userId)
				  .orElseThrow(()-> new ResourceNotFoundException("UserProfile not found with id:" + userId));
		userProfileRepository.delete(userProfile);
        return ResponseEntity.ok().build();
	}
	
}
