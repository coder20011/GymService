package com.cognizant.gym.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.gym.exceptions.InvalidDataException;
import com.cognizant.gym.model.Calories;
import com.cognizant.gym.model.Food;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CaloriesServiceImpl implements CaloriesService {

	@Autowired
	private FoodService foodService;

	/* Kiruthiga */

	@Override
	@Transactional
	public Float calculateTotalCalories(List<Calories> caloriesList) throws InvalidDataException {
		log.info("Calculating total calories");
		if ((caloriesList.size() != 0)) {
			Float totalCalories = 0.0f;
			for (Calories calories : caloriesList) {
				if ((calories.getFoodId() != null) && (calories.getQuantity() != null)) {
					Food food = foodService.getFood(calories.getFoodId());
					Float quantity = calories.getQuantity();
					if (food != null) {
						Float calPerQty = food.getCalPerQty();
						totalCalories += calPerQty * quantity;
					}
				} else {
					throw new InvalidDataException("Data is not valid, either foodId or Quantity is null.");
				}
			}
			return totalCalories;
		} else {
			throw new InvalidDataException("Data is not valid, either foodId or Quantity is null.");
		}
	}

}
