package com.cognizant.gym.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.gym.model.Category;

@Service
public interface CategoryService {

	/* Kiruthiga */

	public List<Category> getAllCategory();

	public Category getCategory(Integer categoryId);

}
