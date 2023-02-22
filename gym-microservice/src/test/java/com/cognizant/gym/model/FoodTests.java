package com.cognizant.gym.model;

import org.hamcrest.MatcherAssert;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FoodTests {
	
	private Food food;
	
	@BeforeEach
	public void setUp() throws Exception {
		food = new Food();
	}
	
	@Test
	public void testFoodId() {
		MatcherAssert.assertThat(food, Matchers.hasProperty("foodId"));
	}
	
	@Test
	public void testFoodName() {
		MatcherAssert.assertThat(food, Matchers.hasProperty("foodName"));
	}
	
	@Test
	public void testcategory() {
		MatcherAssert.assertThat(food, Matchers.hasProperty("category"));
	}
	
	@Test
	public void testCalPerQty() {
		MatcherAssert.assertThat(food, Matchers.hasProperty("calPerQty"));
	}
	
	@Test
	public void testUnits() {
		MatcherAssert.assertThat(food, Matchers.hasProperty("units"));
	}

}
