package com.clinicallyinsane.ClinicServer.controller;

import com.clinicallyinsane.ClinicServer.exception.ResourceNotFoundException;
import com.clinicallyinsane.ClinicServer.model.UserCredentials;
import com.clinicallyinsane.ClinicServer.model.UserProfile;
import com.clinicallyinsane.ClinicServer.repository.UserCredentialsRepository;
import com.clinicallyinsane.ClinicServer.repository.UserProfileRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/u/")
public class UserCredentialsController {
    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;



    @GetMapping("/user/{id}")
    public ResponseEntity<UserCredentials> getUserById(@PathVariable(name = "id")String userId) throws ResourceNotFoundException {
        UserCredentials userCredentials = userCredentialsRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found for this id:" + userId));
        return ResponseEntity.ok().body(userCredentials);
    }

    @PostMapping("/user")
    public UserCredentials userAdd(@Valid @RequestBody UserCredentials userCredentials) {
        return userCredentialsRepository.save(userCredentials);
    }

    @PutMapping("/user/login")
    public ResponseEntity<UserCredentials> updateLogin(@RequestBody UserCredentials userAttempt) throws ResourceNotFoundException{
        String userId = userAttempt.getId();
        String userPw = userAttempt.getPassword();

        UserCredentials userCredentials = userCredentialsRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found"));

        if(userCredentials.getPassword().equals(userPw)) {
            userCredentials.setLoginStatus(1);
            return ResponseEntity.ok().body(userCredentials);
        } else {
            return ResponseEntity.notFound().build();
        }


    }

    @PutMapping("/user/logout/{id}")
    public ResponseEntity<UserCredentials> updateLogout(@PathVariable(name = "id") String userId) throws ResourceNotFoundException {
        UserCredentials account = userCredentialsRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User id not found"));
        account.setLoginStatus(0);
        UserCredentials updatedAccount = account;
        userCredentialsRepository.save(updatedAccount);
        return ResponseEntity.ok().body(updatedAccount);
    }


}
