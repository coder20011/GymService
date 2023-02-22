package com.cognizant.user.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

class UserTests {
	User user = null;

	@BeforeEach
	void setUp() throws Exception {
		user = new User();
	}

	@Test
	void userIdTest() {
		assertThat(user, hasProperty("userId"));
	}

	@Test
	void userNameTest() {
		assertThat(user, hasProperty("userName"));
	}

	@Test
	void passwordTest() {
		assertThat(user, hasProperty("password"));
	}

	@Test
	void confirmPasswordTest() {
		assertThat(user, hasProperty("confirmPassword"));
	}

	@Test
	void firstNameTest() {
		assertThat(user, hasProperty("firstName"));
	}

	@Test
	void lastNameTest() {
		assertThat(user, hasProperty("lastName"));
	}

	@Test
	void contactNumberTest() {
		assertThat(user, hasProperty("contactNumber"));
	}

	@Test
	void emailIdTest() {
		assertThat(user, hasProperty("emailId"));
	}

	@Test
	void userTypeTest() {
		assertThat(user, hasProperty("userType"));
	}

}