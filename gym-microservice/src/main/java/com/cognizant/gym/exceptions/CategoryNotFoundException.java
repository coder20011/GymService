package com.cognizant.gym.exceptions;

/**
 * Class for handling CategoryNotFoundException
 */
public class CategoryNotFoundException extends RuntimeException {
	
	public CategoryNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * This method sets the custom error message
	 * 
	 * @param message
	 */
	private static final long serialVersionUID = 1L;

}
