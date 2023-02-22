package com.cognizant.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test - UserCredentials class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class UserCredentialsTest {

    private UserCredentials userCredentials;

    @BeforeEach
    void setUp() {
        userCredentials = new UserCredentials();
    }

    @Test
    void userNameTest() {
    	assertThat(userCredentials, hasProperty("userName"));
    }

    @Test
    void passwordTest() {
    	assertThat(userCredentials, hasProperty("password"));
    }

}
