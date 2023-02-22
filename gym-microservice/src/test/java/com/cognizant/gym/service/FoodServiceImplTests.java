package com.cognizant.gym.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.gym.exceptions.FoodNotFoundException;
import com.cognizant.gym.model.Category;
import com.cognizant.gym.model.Food;
import com.cognizant.gym.repository.FoodRepository;

@ExtendWith(MockitoExtension.class)
public class FoodServiceImplTests {

	@Mock
	private FoodRepository foodRepository;

	@InjectMocks
	private FoodServiceImpl foodServiceImpl;

	@Test
	public void testGetAllFoodSuccess() throws FoodNotFoundException {
		// Arrange
		List<Food> expectedFoodList = Arrays.asList(
				new Food(1, "Food 1", new Category(1, "Category 1", null), 100.0f, "grams"),
				new Food(2, "Food 2", new Category(2, "Category 2", null), 200.0f, "ml"));
		Mockito.when(foodRepository.findAll()).thenReturn(expectedFoodList);

		// Act
		List<Food> actualFoodList = foodServiceImpl.getAllFood();

		// Assert
		assertEquals(expectedFoodList, actualFoodList);
		Mockito.verify(foodRepository, Mockito.times(1)).findAll();
	}

	@Test
	public void testGetAllFoodEmptyDatabase() throws FoodNotFoundException {
		// Arrange
		List<Food> expectedFoodList = new ArrayList<>();
		Mockito.when(foodRepository.findAll()).thenReturn(expectedFoodList);

		// Act & Assert
		assertThrows(FoodNotFoundException.class, () -> foodServiceImpl.getAllFood());
		Mockito.verify(foodRepository, Mockito.times(1)).findAll();
	}

	@Test
	public void testGetFoodByCategorySuccess() throws FoodNotFoundException {
		// Given
		Integer foodCatId = 1;
		Food food = new Food(1, "Apple", new Category(foodCatId, "Fruit", null), 50.0f, "g");
		List<Food> expectedFoodList = Arrays.asList(food);
		when(foodRepository.findByCategory(foodCatId)).thenReturn(expectedFoodList);

		// When
		List<Food> actualFoodList = foodServiceImpl.getFoodByCategory(foodCatId);

		// Then
		verify(foodRepository).findByCategory(foodCatId);
		assertEquals(expectedFoodList, actualFoodList);
	}

	@Test
	public void testGetFoodByCategoryNotFoodFound() {
		// Given
		Integer foodCatId = 2;
		when(foodRepository.findByCategory(foodCatId)).thenReturn(Collections.emptyList());

		// When and Then
		assertThrows(FoodNotFoundException.class, () -> foodServiceImpl.getFoodByCategory(foodCatId));
	}

	@Test
	void testGetFoodSuccess() {
		// Given
		Integer foodId = 1;
		Food food = new Food();
		food.setFoodId(foodId);
		food.setFoodName("Pizza");
		food.setCalPerQty(450f);
		food.setUnits("grams");

		when(foodRepository.findById(foodId)).thenReturn(Optional.of(food));

		// When
		Food returnedFood = foodServiceImpl.getFood(foodId);

		// Then
		assertThat(returnedFood).isEqualTo(food);
	}

	@Test
	void testGetFoodFoodNotFoundException() {
		// Given
		Integer foodId = 1;
		when(foodRepository.findById(foodId)).thenReturn(Optional.empty());

		// When
		FoodNotFoundException exception = Assertions.assertThrows(FoodNotFoundException.class, () -> {
			foodServiceImpl.getFood(foodId);
		});

		// Then
		assertThat(exception.getMessage()).isEqualTo("Food with the id: " + foodId + " not found.");
	}

}
