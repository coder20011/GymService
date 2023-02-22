package com.cognizant.gym.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test - GlobalExceptionHandler class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @Mock
    CustomErrorResponse customErrorResponse;

    @BeforeEach
    void setUp() {
        customErrorResponse = new CustomErrorResponse();
    }

    @Test
    void handlesCategoryNotFoundException() {
    	CategoryNotFoundException categoryNotFoundException = new CategoryNotFoundException("Category not found");
        globalExceptionHandler.handlesCategoryNotFoundException(categoryNotFoundException);
        customErrorResponse.setDateTime(LocalDateTime.now());
        customErrorResponse.setMessage(categoryNotFoundException.getMessage());

        ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
        assertEquals(401, entity.getStatusCodeValue());
    }

    @Test
    void handlesFoodNotFoundException() {
    	FoodNotFoundException foodNotFoundException = new FoodNotFoundException("Food not found");
        globalExceptionHandler.handlesFoodNotFoundException(foodNotFoundException);
        customErrorResponse.setDateTime(LocalDateTime.now());
        customErrorResponse.setMessage(foodNotFoundException.getMessage());

        ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
        assertEquals(401, entity.getStatusCodeValue());
    }
    
    @Test
    void handlesInvalidDataException() {
    	InvalidDataException invalidDataException = new InvalidDataException("Data is not found");
        globalExceptionHandler.handlesInvalidDataException(invalidDataException);
        customErrorResponse.setDateTime(LocalDateTime.now());
        customErrorResponse.setMessage(invalidDataException.getMessage());

        ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
        assertEquals(401, entity.getStatusCodeValue());
    }

}
