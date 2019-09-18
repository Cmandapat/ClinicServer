package com.clinicallyinsane.ClinicServer.controller;

import java.util.List;

import javax.validation.Valid;

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
@RestController
@RequestMapping("/api/up")
public class UserProfileController {
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
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
	public ResponseEntity<UserProfile> getUserProfileById(@PathVariable(value = "id") Long userId, 
														  @Valid @RequestBody UserProfile userProfileDetails)
													      throws ResourceNotFoundException{
		
		UserProfile userProfile = userProfileRepository.findById(userId)
								  .orElseThrow(()-> new ResourceNotFoundException("UserProfile not found with id:" + userId));

		return ResponseEntity.ok().body(userProfile);
	}
	
	
	
}
