package com.cognizant.user.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserNotFoundExceptionTest {

    @Mock
    UserNotFoundException userNotFoundException;

    @Test
    void testOneArgConstructor() {
        UserNotFoundException userNotFoundException = new UserNotFoundException("User not found");
        assertEquals("User not found", userNotFoundException.getMessage());
    }

}