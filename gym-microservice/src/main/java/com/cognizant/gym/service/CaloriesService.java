package com.cognizant.gym.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.gym.model.Calories;

@Service
public interface CaloriesService {

	/* Kiruthiga */

	public Float calculateTotalCalories(List<Calories> caloriesList);

}
