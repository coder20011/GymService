package com.cognizant.gym.exceptions;

/**
 * Class for handling FoodNotFoundException
 */
public class FoodNotFoundException extends RuntimeException {
	
	public FoodNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * This method sets the custom error message
	 * 
	 * @param message
	 */
	private static final long serialVersionUID = 1L;

}
