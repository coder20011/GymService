package com.cognizant.gym.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test - CustomErrorResponse class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class CustomErrorResponseTest {

    @Mock
    CustomErrorResponse customErrorResponse;

    @BeforeEach
    void setUp() {
        customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setDateTime(LocalDateTime.now());
        customErrorResponse.setMessage("Custom error response");
    }

    @Test
    void CustomErrorResponseAllConstructorTest() {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse("Custom error response", LocalDateTime.now());
        assertEquals("Custom error response", customErrorResponse.getMessage());
    }

    @Test
    void messageTest() {
        assertEquals("Custom error response", customErrorResponse.getMessage());
    }

    @Test
    void dateTimeTest() {
        customErrorResponse.setDateTime(LocalDateTime.now());
        assertEquals(LocalDateTime.now(), customErrorResponse.getDateTime());
    }

    @Test
    void toStringTest() {
        String string = customErrorResponse.toString();
        assertEquals(string, customErrorResponse.toString());
    }

    @Test
    void testEqualsMethod() {
        boolean equals = customErrorResponse.equals(customErrorResponse);
        assertTrue(equals);
    }

    @Test
    void testHashCodeMethod() {
        int hashCode = customErrorResponse.hashCode();
        assertEquals(hashCode, customErrorResponse.hashCode());
    }

}