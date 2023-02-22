package com.cognizant.gym.model;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTests {
	
	private Category category;
	
	@BeforeEach
	public void setUp() throws Exception {
		category = new Category();
	}
	
	@Test
	public void testCategoryId() {
		MatcherAssert.assertThat(category, Matchers.hasProperty("categoryId"));
	}
	
	@Test
	public void testCategoryName() {
		MatcherAssert.assertThat(category, Matchers.hasProperty("categoryName"));
	}
	
	@Test
	public void testFoodList() {
		MatcherAssert.assertThat(category, Matchers.hasProperty("foodList"));
	}

}
