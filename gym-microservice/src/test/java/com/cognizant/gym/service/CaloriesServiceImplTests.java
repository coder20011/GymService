package com.cognizant.gym.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.gym.exceptions.InvalidDataException;
import com.cognizant.gym.model.Calories;
import com.cognizant.gym.model.Food;

@ExtendWith(MockitoExtension.class)
public class CaloriesServiceImplTests {

	@Mock
	private FoodService foodService;

	@InjectMocks
	private CaloriesServiceImpl calorieService;

	@Test
	public void testCalculateTotalCalories() throws InvalidDataException {
		List<Calories> caloriesList = new ArrayList<>();
		Calories calories = new Calories();
		calories.setFoodId(1);
		calories.setQuantity(2.0f);
		caloriesList.add(calories);

		Food food = new Food();
		food.setCalPerQty(100.0f);
		when(foodService.getFood(1)).thenReturn(food);

		Float totalCalories = calorieService.calculateTotalCalories(caloriesList);

		assertEquals(200.0f, totalCalories);
	}

	@Test
	public void testCalculateTotalCaloriesWithInvalidDataException() {
		List<Calories> caloriesList = new ArrayList<>();
		assertThrows(InvalidDataException.class, () -> calorieService.calculateTotalCalories(caloriesList));
	}

}
