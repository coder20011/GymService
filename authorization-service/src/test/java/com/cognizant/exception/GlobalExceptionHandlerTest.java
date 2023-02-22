package com.cognizant.exception;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void handlesUserNameNumericException() {
        UserNameNumericException userNameNumericException = new UserNameNumericException("User name is numeric");
        globalExceptionHandler.handlesUserNameNumericException(userNameNumericException);
        customErrorResponse.setDateTime(LocalDateTime.now());
        customErrorResponse.setMessage(userNameNumericException.getMessage());

        ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
        assertEquals(401, entity.getStatusCodeValue());
    }

    @Test
    void handlesUserNotFoundException() {
        UserNotFoundException userNotFoundException = new UserNotFoundException("User not found");
        globalExceptionHandler.handlesUserNotFoundException(userNotFoundException);
        customErrorResponse.setDateTime(LocalDateTime.now());
        customErrorResponse.setMessage(userNotFoundException.getMessage());

        ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
        assertEquals(401, entity.getStatusCodeValue());
    }

}
