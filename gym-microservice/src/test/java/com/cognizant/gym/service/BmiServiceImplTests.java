package com.cognizant.gym.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BmiServiceImplTests {

	@InjectMocks
	private BmiServiceImpl bmiService;

	@Test
	public void testCalculateBmiUnderweight() {
		float weight = 50.0f;
		float height = 1.75f;

		String expectedResult = "Your BMI Score is 16.33 and your BMI Status is Underweight";

		String result = bmiService.calculateBmi(weight, height);

		assertEquals(expectedResult, result);
	}

	@Test
	public void testCalculateBmiNormalWeight() {
		float weight = 70.0f;
		float height = 1.75f;

		String expectedResult = "Your BMI Score is 22.86 and your BMI Status is Normal weight";

		String result = bmiService.calculateBmi(weight, height);

		assertEquals(expectedResult, result);
	}
}
