package com.cognizant.gym.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryNotFoundExceptionTests {
	
	@Mock
	private CategoryNotFoundException categoryNotFoundException;
	
	@Test
	public void testOneArgConstructor() {
		categoryNotFoundException = new CategoryNotFoundException("Category not found");
		assertEquals("Category not found", categoryNotFoundException.getMessage());
	}

}
