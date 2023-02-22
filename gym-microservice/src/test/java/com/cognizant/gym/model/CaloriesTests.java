package com.cognizant.gym.model;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaloriesTests {

	private Calories calories;

	@BeforeEach
	public void setUp() throws Exception {
		calories = new Calories();
	}

	@Test
	public void testFoodId() {
		MatcherAssert.assertThat(calories, Matchers.hasProperty("foodId"));
	}

	@Test
	public void testQuantity() {
		MatcherAssert.assertThat(calories, Matchers.hasProperty("quantity"));
	}

}
