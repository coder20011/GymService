package com.cognizant.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.gym.feign.AuthorizationClient;
import com.cognizant.gym.model.Food;
import com.cognizant.gym.service.FoodService;

@RestController
@CrossOrigin
@RequestMapping("/food")
public class FoodController {

	@Autowired
	private FoodService foodService;

	@Autowired
	private AuthorizationClient authorizationClient;

	/* Kiruthiga */

	@GetMapping("/{foodId}")
	public ResponseEntity<Food> getFood(@RequestHeader(name = "Authorization") String token,
			@PathVariable Integer foodId) {
		ResponseEntity<Food> entity = null;
		if (authorizationClient.validate(token)) {
			entity = new ResponseEntity<Food>(foodService.getFood(foodId), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	@GetMapping
	public ResponseEntity<List<Food>> getAllFood(@RequestHeader(name = "Authorization") String token) {
		ResponseEntity<List<Food>> entity = null;
		if (authorizationClient.validate(token)) {
			entity = new ResponseEntity<List<Food>>(foodService.getAllFood(), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	@GetMapping("/category/{foodCatId}")
	public ResponseEntity<List<Food>> getFoodByCategory(@PathVariable Integer foodCatId,
			@RequestHeader(name = "Authorization") String token) {
		ResponseEntity<List<Food>> entity = null;
		if (authorizationClient.validate(token)) {
			entity = new ResponseEntity<List<Food>>(foodService.getFoodByCategory(foodCatId), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

}
