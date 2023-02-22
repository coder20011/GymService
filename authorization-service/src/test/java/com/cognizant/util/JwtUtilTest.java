package com.cognizant.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * test - JwtUtil class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class JwtUtilTest {

    UserDetails userDetails;

    @InjectMocks
    JwtUtil jwtUtil;

    @Test
    void isNumericTrueTest() {
        String num = "123";
        assertEquals(true, jwtUtil.isNumeric(num));
    }

    @Test
    void isNumericNullTest() {
        String num = null;
        assertEquals(false, jwtUtil.isNumeric(num));
    }

    @Test
    void isNumericFalseTest() {
        String num = "abc";
        assertEquals(false, jwtUtil.isNumeric(num));
    }

    @Test
    void generateTokenTest() {
        userDetails = new User("admin", "admin", new ArrayList<>());
        String generateToken = jwtUtil.generateToken(userDetails.getUsername());
        assertNotNull(generateToken);
    }

    @Test
    void validateTokenTest() {
        userDetails = new User("admin", "pass", new ArrayList<>());
        String generateToken = jwtUtil.generateToken(userDetails.getUsername());
        boolean validateToken = jwtUtil.validateToken(generateToken, userDetails);
        assertEquals(true, validateToken);
    }

    @Test
    void validateTokenWithNameTest() {
        userDetails = new User("vishal", "password", new ArrayList<>());
        String generateToken = jwtUtil.generateToken(userDetails.getUsername());
        Boolean validateToken = jwtUtil.validateToken(generateToken, userDetails);
        assertEquals(true, validateToken);
    }

    @Test
    void validateTokenWithNameFalseTest() {
        userDetails = new User("pass", "admin", new ArrayList<>());
        String generateToken = jwtUtil.generateToken(userDetails.getUsername());
        Boolean validateToken = jwtUtil.validateToken(generateToken, userDetails);
        assertEquals(true, validateToken);
    }

}
