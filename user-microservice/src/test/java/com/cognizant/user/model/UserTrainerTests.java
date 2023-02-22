package com.cognizant.user.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTrainerTests {
	UserTrainer userTrainer = null;

	@BeforeEach
	void setUp() throws Exception {
		userTrainer = new UserTrainer();
	}

	@Test
	void transcationIdTest() {
		assertThat(userTrainer, hasProperty("transactionId"));
	}

	@Test
	void userIdTest() {
		assertThat(userTrainer, hasProperty("userId"));
	}

	@Test
	void trainerIdTest() {
		assertThat(userTrainer, hasProperty("trainerId"));
	}

}