package com.cognizant.gym.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.gym.exceptions.CategoryNotFoundException;
import com.cognizant.gym.model.Category;
import com.cognizant.gym.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	/* Kiruthiga */

	@Override
	@Transactional
	public List<Category> getAllCategory() throws CategoryNotFoundException {
		log.info("Getting all Categories");
		List<Category> categoryList = categoryRepository.findAll();
		if (categoryList.size() == 0) {
			log.info("Exception occured");
			throw new CategoryNotFoundException("Database is empty");
		} else {
			return categoryList;
		}
	}

	@Override
	@Transactional
	public Category getCategory(Integer categoryId) {
		log.info("Getting Category by Id");
		Category category = categoryRepository.findById(categoryId).orElseThrow(
				() -> new CategoryNotFoundException("Category with the id: " + categoryId + " not found."));
		return category;
	}

}
