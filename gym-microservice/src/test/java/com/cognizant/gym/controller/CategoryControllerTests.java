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

import com.cognizant.gym.exceptions.CategoryNotFoundException;
import com.cognizant.gym.feign.AuthorizationClient;
import com.cognizant.gym.model.Category;
import com.cognizant.gym.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTests {
	
	@MockBean
	private CategoryService categoryService;

	@MockBean
	private AuthorizationClient authorizationClient;

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	private Category category;
	private List<Category> categoryList;

	@Test
	public void contextLoads() {
		assertNotNull(FoodController.class);
	}

	@BeforeEach
	public void setUp() throws Exception {

		category = new Category();
		category.setCategoryId(6);
		category.setCategoryName("Miscellaneous");
		category.setFoodList(new HashSet<>());

		when(authorizationClient.validate("henryadmin1")).thenReturn(true);

		when(categoryService.getCategory(6)).thenReturn(category);
		when(categoryService.getCategory(7)).thenThrow(new CategoryNotFoundException("Category with the id: 7 not found."));

		categoryList = new ArrayList<>();
		categoryList.add(category);

		Category category1 = new Category();
		category1.setCategoryId(8);
		category1.setCategoryName("Sweets");
		category1.setFoodList(new HashSet<>());

		categoryList.add(category1);

		when(categoryService.getAllCategory()).thenReturn(categoryList);
	}

	@Test
	public void testGetCategory() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/category/6").header("Authorization", "henryadmin1").accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$.categoryName").value("Miscellaneous"));
	}

	@Test
	public void testGetCategoryCategoryNotFoundException() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/food/7").header("Authorization", "henryadmin1").accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isNotFound());
	}

	@Test
	public void testGetCategoryAuthorizationExeption() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/category/6").header("Authorization", "newuser").accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isForbidden());
	}

	@Test
	public void testGetAllCategory() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/category").header("Authorization", "henryadmin1").accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$[0].categoryId", is(6)));
		resultActions.andExpect(jsonPath("$[1].categoryId", is(8)));
	}

	@Test
	public void testGetAllCategoryCategoryNotFoundException() throws Exception {
		when(categoryService.getAllCategory()).thenThrow(new CategoryNotFoundException("Database is empty"));
		ResultActions resultActions = mockMvc.perform(get("/category").header("Authorization", "henryadmin1")
				.accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isNotFound());
	}

	@Test
	public void testGetAllCategoryAuthorizationExeption() throws Exception {
		ResultActions resultActions = mockMvc
				.perform(get("/category").header("Authorization", "newuser").accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isForbidden());
	}

}
