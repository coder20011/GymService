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
import com.cognizant.gym.model.Category;
import com.cognizant.gym.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AuthorizationClient authorizationClient;

	/* Kiruthiga */
	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategory(@RequestHeader(name = "Authorization") String token,
			@PathVariable Integer categoryId) {
		ResponseEntity<Category> entity = null;
		if (authorizationClient.validate(token)) {
			entity = new ResponseEntity<Category>(categoryService.getCategory(categoryId), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

	@GetMapping
	public ResponseEntity<List<Category>> getAllCategory(@RequestHeader(name = "Authorization") String token) {
		ResponseEntity<List<Category>> entity = null;
		if (authorizationClient.validate(token)) {
			entity = new ResponseEntity<List<Category>>(categoryService.getAllCategory(), HttpStatus.OK);
		} else {
			entity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return entity;
	}

}
