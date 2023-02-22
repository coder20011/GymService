package com.cognizant.gym.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.cognizant.gym.exceptions.FoodNotFoundException;
import com.cognizant.gym.feign.AuthorizationClient;
import com.cognizant.gym.model.Category;
import com.cognizant.gym.model.Food;
import com.cognizant.gym.service.FoodService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(FoodController.class)
public class FoodControllerTests {

	@MockBean
	private FoodService foodService;

	@MockBean
	private AuthorizationClient authorizationClient;

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	private Food food;
	private Category category;
	private List<Food> foodList;

	@Test
	public void contextLoads() {
		assertNotNull(FoodController.class);
	}

	@BeforeEach
	public void setUp() throws Exception {

		food = new Food();
		food.setFoodId(31);
		food.setFoodName("Wheat");
		food.setCalPerQty(130.0f);
		food.setUnits("1cup");

		category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("Lunch");
		category.setFoodList(new HashSet<>());

		food.setCategory(category);

		when(authorizationClient.validate("henryadmin1")).thenReturn(true);

		when(foodService.getFood(31)).thenReturn(food);
		when(foodService.getFood(32)).thenThrow(new FoodNotFoundException("Food with the id: 32 not found."));

		foodList = new ArrayList<>();
		foodList.add(food);

		Food food1 = new Food();
		food1.setFoodId(33);
		food1.setFoodName("Jam");
		food1.setCalPerQty(110.0f);
		food1.setUnits("1tbsp");

		Category category1 = new Category();
		category1.setCategoryId(6);
		category1.setCategoryName("Miscellaneous");
		category1.setFoodList(new HashSet<>());

		food1.setCategory(category1);

		foodList.add(food1);

		when(foodService.getAllFood()).thenReturn(foodList);
	}

	@Test
	public void testGetFood() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/food/31").header("Authorization", "henryadmin1").accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.foodName").value("Wheat"));
	}

	@Test
	public void testGetFoodFoodNotFoundException() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/food/32").header("Authorization", "henryadmin1").accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isNotFound());
	}

	@Test
	public void testGetFoodAuthorizationExeption() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/food/31").header("Authorization", "newuser").accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isForbidden());
	}

	@Test
	public void testGetAllFood() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/food").header("Authorization", "henryadmin1").accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$[0].foodId", is(31)));
		resultActions.andExpect(jsonPath("$[1].foodId", is(33)));
	}

	@Test
	public void testGetAllFoodFoodNotFoundException() throws Exception {
		when(foodService.getAllFood()).thenThrow(new FoodNotFoundException("Database is empty"));
		ResultActions resultActions = mockMvc.perform(get("/food").header("Authorization", "henryadmin1")
				.accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isNotFound());
	}

	@Test
	public void testGetAllFoodAuthorizationExeption() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/food").header("Authorization", "newuser").accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isForbidden());
	}

}
