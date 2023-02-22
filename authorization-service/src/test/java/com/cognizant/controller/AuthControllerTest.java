package com.cognizant.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.exception.UserNameNumericException;
import com.cognizant.exception.UserNotFoundException;
import com.cognizant.model.MyUser;
import com.cognizant.model.UserCredentials;
import com.cognizant.repository.UserRepo;
import com.cognizant.service.UserDetailsServiceImpl;
import com.cognizant.util.JwtUtil;

/**
 * Test - AuthController class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthControllerTest {

	@InjectMocks
	private AuthController authController;

	private UserDetails userdetails;

	@Mock
	private JwtUtil jwtutil;

	@Mock
	private UserDetailsServiceImpl custdetailservice;

	@Mock
	private UserRepo userservice;

	@Test
	public void validLoginTest() {

		UserCredentials user = new UserCredentials("admin", "admin");
		UserDetails value = new User(user.getUserName(), user.getPassword(), new ArrayList<>());
		when(custdetailservice.loadUserByUsername("admin")).thenReturn(value);
		when(jwtutil.generateToken(user.getUserName())).thenReturn("token");
		ResponseEntity<String> login = authController.login(user);
	}


	@Test
	public void userNameNotFoundLoginTest() {
		UserCredentials user = new UserCredentials("123", "abc");
		Exception exception = assertThrows(UserNotFoundException.class, () -> {
			authController.login(user);
		});

		String expectedMessage = "Invalid Credential";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void validateTestValidtoken() {

		UserCredentials user = new UserCredentials("admin", "admin");
		UserDetails value = new User(user.getUserName(), "admin", new ArrayList<>());

		when(jwtutil.validateToken("token", value)).thenReturn(true);
		when(jwtutil.extractUsername("token")).thenReturn("admin");

		MyUser user1 = new MyUser(1, "admin", "admin");
		Optional<MyUser> data = Optional.of(user1);

		when(userservice.findById(1)).thenReturn(data);

		ResponseEntity<?> validity = authController.validate("Bearer token");

		assertEquals(false, validity.getBody().toString().contains("true"));

	}

	@Test
	public void validateTestInValidUsertoken() {

		UserCredentials user = new UserCredentials("admin", "admin");
		UserDetails value = new User(user.getUserName(), "admin", new ArrayList<>());

		when(jwtutil.validateToken("token1", value)).thenReturn(false);

		ResponseEntity<?> validity = authController.validate("Bearer token1");

		assertEquals(true, validity.getBody().toString().contains("false"));

	}

	@Test
	public void validateTestInValidtoken() {

		UserCredentials user = new UserCredentials("admin", "admin");
		UserDetails value = new User(user.getUserName(), "admin", new ArrayList<>());

		when(jwtutil.validateToken("token", value)).thenReturn(false);

		ResponseEntity<?> validity = authController.validate("bearer token");

		assertEquals(true, validity.getBody().toString().contains("false"));

	}

	@Test
	public void testHealthCheck() {
		assertEquals(200, authController.healthCheck().getStatusCodeValue());
	}

}
