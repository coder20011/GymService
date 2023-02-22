package com.cognizant.gym.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FoodNotFoundExceptionTests {

	@Mock
	private FoodNotFoundException foodNotFoundException;

	@Test
	public void testOneArgConstructor() {
		foodNotFoundException = new FoodNotFoundException("Food not found");
		assertEquals("Food not found", foodNotFoundException.getMessage());
	}

}
