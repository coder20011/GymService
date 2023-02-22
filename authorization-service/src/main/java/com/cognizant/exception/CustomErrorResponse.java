package com.cognizant.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is for customizing the exception handler
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {

	private String message;
	private LocalDateTime dateTime;
}
