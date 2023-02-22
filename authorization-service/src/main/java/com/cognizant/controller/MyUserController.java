package com.cognizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.MyUser;
import com.cognizant.service.UserServiceImpl;

import com.cognizant.model.MyUser;

@RestController
@CrossOrigin
public class MyUserController {

	@Autowired
	private UserServiceImpl userService;

	/* Athira */

	@PostMapping("/user/{userName}/{password}")
	public ResponseEntity<String> addUser(@PathVariable(name = "userName") String userName,
			@PathVariable(name = "password") String password) {
		MyUser user = new MyUser();
		user.setUserName(userName);
		user.setPassword(password);
		userService.insertUser(user);
		return new ResponseEntity<>("Inserted", HttpStatus.CREATED);
	}
	
}
