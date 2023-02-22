package com.cognizant.gym.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global Exception Handler class
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handles invalid data exception
	 * 
	 * @param ex
	 * @return This returns the custom error message and the time stamp and the HTTP
	 *         status code
	 */
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<CustomErrorResponse> handlesInvalidDataException(InvalidDataException invalidDataException) {
		CustomErrorResponse response = new CustomErrorResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(invalidDataException.getMessage());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles category not found exception
	 * 
	 * @param ex
	 * @return This returns the custom error message and the time stamp and the HTTP
	 *         status code
	 */
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handlesCategoryNotFoundException(
			CategoryNotFoundException categoryNotFoundException) {
		CustomErrorResponse response = new CustomErrorResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(categoryNotFoundException.getMessage());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles food not found exception
	 * 
	 * @param ex
	 * @return This returns the custom error message and the time stamp and the HTTP
	 *         status code
	 */
	@ExceptionHandler(FoodNotFoundException.class)
	public ResponseEntity<CustomErrorResponse> handlesFoodNotFoundException(
			FoodNotFoundException foodNotFoundException) {
		CustomErrorResponse response = new CustomErrorResponse();
		response.setDateTime(LocalDateTime.now());
		response.setMessage(foodNotFoundException.getMessage());

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
