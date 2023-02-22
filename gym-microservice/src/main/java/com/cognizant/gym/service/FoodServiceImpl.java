package com.cognizant.gym.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.gym.exceptions.CategoryNotFoundException;
import com.cognizant.gym.exceptions.FoodNotFoundException;
import com.cognizant.gym.model.Food;
import com.cognizant.gym.repository.FoodRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;

	/* Kiruthiga */

	@Override
	@Transactional
	public List<Food> getAllFood() throws CategoryNotFoundException {
		log.info("Getting all Foods");
		List<Food> foodList = foodRepository.findAll();
		if (foodList.size() == 0) {
			log.info("Exception occured");
			throw new FoodNotFoundException("Database is empty");
		} else {
			return foodList;
		}
	}

	@Override
	@Transactional
	public List<Food> getFoodByCategory(Integer foodCatId) {
		List<Food> foodList = foodRepository.findByCategory(foodCatId);
		if (foodList.size() == 0) {
			log.info("Exception occured");
			throw new FoodNotFoundException("No Food found under this category");
		} else {
			return foodList;
		}
	}

	@Override
	@Transactional
	public Food getFood(Integer foodId) {
		log.info("Getting Food by Id");
		Food food = foodRepository.findById(foodId)
				.orElseThrow(() -> new FoodNotFoundException("Food with the id: " + foodId + " not found."));
		return food;
	}

}
