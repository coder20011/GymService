package com.cognizant.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.user.feign.AuthorizationClient;
import com.cognizant.user.model.User;
import com.cognizant.user.model.UserTrainer;
import com.cognizant.user.repository.UserTrainerRepository;
import com.cognizant.user.service.UserTrainerServiceImpl;

@RestController
@CrossOrigin
public class UserTrainerController {

	@Autowired
	private UserTrainerRepository userTrainerRepository;
	
	@Autowired
	private UserTrainerServiceImpl userTrainerService;

	@Autowired
	private AuthorizationClient authClient;

	/*Athira*/
	@GetMapping("/trainer/{userId}")
	public ResponseEntity<User> viewAssignedTrainer(@RequestHeader("Authorization") String token,
			@PathVariable int userId) {
		ResponseEntity<User> entity = null;
		User trainer = userTrainerService.getAssignedTrainer(userId);
		if (authClient.validate(token)) {
			entity = new ResponseEntity<>(trainer, HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	/*Labeeb*/
	@GetMapping("/users/{trainerId}")
	public ResponseEntity<List<User>> viewAssignedUsers(@RequestHeader("Authorization") String token,
			@PathVariable int trainerId) {
		ResponseEntity<List<User>> entity = null;
		List<User> users = userTrainerService.viewAssignedUsers(trainerId);
		if (authClient.validate(token)) {
			entity = new ResponseEntity<>(users, HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	/*Vamsee*/
	@PostMapping("/assign")
	public ResponseEntity<String> assignTrainer(@RequestHeader("Authorization") String token,
			@RequestBody UserTrainer userTrainer) {
		ResponseEntity<String> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<>(userTrainerService.assignTrainer(userTrainer), HttpStatus.ACCEPTED);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

}