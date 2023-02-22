package com.cognizant.gym.exceptions;

/**
 * Class for handling InvalidDataException
 */
public class InvalidDataException extends RuntimeException {
	
	public InvalidDataException(String message) {
		super(message);
	}
	
	/**
	 * This method sets the custom error message
	 * 
	 * @param message
	 */
	private static final long serialVersionUID = 1L;

}
