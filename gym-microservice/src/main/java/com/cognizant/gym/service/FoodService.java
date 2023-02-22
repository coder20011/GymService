package com.cognizant.gym.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.gym.model.Food;

@Service
public interface FoodService {

	/* Kiruthiga */

	public List<Food> getAllFood();

	public List<Food> getFoodByCategory(Integer foodCatId);

	public Food getFood(Integer foodId);

}
