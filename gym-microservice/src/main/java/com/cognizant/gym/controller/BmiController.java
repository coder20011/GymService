package com.cognizant.gym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.gym.feign.AuthorizationClient;
import com.cognizant.gym.model.Bmi;
import com.cognizant.gym.service.BmiService;

@RestController
@CrossOrigin
public class BmiController {

	@Autowired
	private BmiService bmiService;

	@Autowired
	private AuthorizationClient authorizationClient;

	/*Vamsee*/
	@PostMapping("/bmi") // Rest EndPoint
	public ResponseEntity<String> calculateBmi(@RequestHeader(name = "Authorization") String token,
			@RequestBody Bmi bmi) {
		ResponseEntity<String> entity = null;
		if (authorizationClient.validate(token)) {
			entity = new ResponseEntity<String>(bmiService.calculateBmi(bmi.getWeight(), bmi.getHeight()),
					HttpStatus.ACCEPTED);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}
}
