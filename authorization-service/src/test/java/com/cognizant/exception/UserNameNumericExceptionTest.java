package com.cognizant.exception;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test - UserNameNumericException class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class UserNameNumericExceptionTest {

    @Mock
    UserNameNumericException userNameNumericException;

    @Test
    void testOneArgConstructor() {
        UserNameNumericException userNameNumericException = new UserNameNumericException("User name is numeric");
        assertEquals("User name is numeric", userNameNumericException.getMessage());
    }
}
