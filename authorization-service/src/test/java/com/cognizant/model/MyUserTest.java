package com.cognizant.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test - MyUser class
 */
class MyUserTest {

    MyUser myUser = null;

    @BeforeEach
    void setUp() {
        myUser = new MyUser();
    }

    @Test
    void userIdTest() {
    	assertThat(myUser, hasProperty("userId"));
    }

    @Test
    void userNameTest() {
    	assertThat(myUser, hasProperty("userName"));
    }

    @Test
    void passwordTest() {
    	assertThat(myUser, hasProperty("password"));
    }

}
