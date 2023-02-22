package com.cognizant.gym.model;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BmiTests {
	
	private Bmi bmi;

	@BeforeEach
	public void setUp() throws Exception {
		bmi = new Bmi();
	}

	@Test
	public void testFoodId() {
		MatcherAssert.assertThat(bmi, Matchers.hasProperty("height"));
	}

	@Test
	public void testQuantity() {
		MatcherAssert.assertThat(bmi, Matchers.hasProperty("weight"));
	}

}
