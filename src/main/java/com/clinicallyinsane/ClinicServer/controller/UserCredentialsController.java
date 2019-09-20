package com.clinicallyinsane.ClinicServer.controller;

import com.clinicallyinsane.ClinicServer.exception.ResourceNotFoundException;
import com.clinicallyinsane.ClinicServer.model.UserCredentials;
import com.clinicallyinsane.ClinicServer.repository.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/u/login")
public class UserCredentialsController {
    @Autowired
    private UserCredentialsRepository userCredentialsRepository;


    @GetMapping("/user/{id}")
    public ResponseEntity<UserCredentials> getUserById(@PathVariable(name = "id")String userId) throws ResourceNotFoundException {
        UserCredentials userCredentials = userCredentialsRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found for this id:" + userId));
        return ResponseEntity.ok().body(userCredentials);
    }

    @PostMapping("/user")
    public UserCredentials userAdd(@Valid @RequestBody UserCredentials userCredentials) {
        return userCredentialsRepository.save(userCredentials);
    }


}
