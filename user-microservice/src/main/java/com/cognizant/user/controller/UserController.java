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
import com.cognizant.user.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorizationClient authClient;

	/* Athira */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {

		authClient.addUser(user.getUserName(), user.getPassword());

		userService.registerUser(user);
		return new ResponseEntity<String>("Inserted", HttpStatus.CREATED);
	}

	@GetMapping("/userType/{userName}/{password}")
	public String getUserType(@PathVariable("userName") String userName, @PathVariable("password") String password) {
		return userService.getUserType(userName, password);
	}

	/* Kiruthiga */
	@GetMapping("/userId/{userName}/{password}")
	public ResponseEntity<Integer> getUserId(@PathVariable String userName, @PathVariable String password) {
		return new ResponseEntity<Integer>(userService.getUserId(userName, password), HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<User> viewUser(@RequestHeader(name = "Authorization") String token,
			@PathVariable int userId) {
		ResponseEntity<User> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<>(userService.viewUser(userId), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	/* Labeeb */
	@GetMapping("/users")
	public ResponseEntity<List<User>> viewAllUsers(@RequestHeader(name = "Authorization") String token) {
		ResponseEntity<List<User>> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<>(userService.viewAllUsers(), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	@GetMapping("/trainers")
	public ResponseEntity<List<User>> viewAllTrainers(@RequestHeader(name = "Authorization") String token) {
		ResponseEntity<List<User>> entity = null;
		if (authClient.validate(token)) {
			entity = new ResponseEntity<>(userService.viewAllTrainers(), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}
}