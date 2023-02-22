package com.cognizant.gym.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class InvalidDataExceptionTest {
	
	@Mock
	private InvalidDataException invalidDataException;
	
	@Test
	public void testOneArgConstructor() {
		invalidDataException = new InvalidDataException("Data is not valid");
		assertEquals("Data is not valid", invalidDataException.getMessage());
	}

}
