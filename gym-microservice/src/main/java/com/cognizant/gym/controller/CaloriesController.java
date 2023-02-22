package com.cognizant.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.gym.feign.AuthorizationClient;
import com.cognizant.gym.model.Calories;
import com.cognizant.gym.service.CaloriesService;

@RestController
@CrossOrigin
public class CaloriesController {

	@Autowired
	private CaloriesService caloriesService;

	@Autowired
	private AuthorizationClient authorizationClient;

	/* Kiruthiga */

	@PostMapping("/calories")
	public ResponseEntity<String> calculateTotalCalories(@RequestHeader(name = "Authorization") String token,
			@RequestBody List<Calories> caloriesList) {
		ResponseEntity<String> entity = null;
		if (authorizationClient.validate(token)) {
			Float result = caloriesService.calculateTotalCalories(caloriesList);
			entity = new ResponseEntity<String>("Your total Calories is " + result, HttpStatus.ACCEPTED);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

}