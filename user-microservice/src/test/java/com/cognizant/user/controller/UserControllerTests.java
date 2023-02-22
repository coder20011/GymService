package com.cognizant.user.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.user.feign.AuthorizationClient;
import com.cognizant.user.model.User;
import com.cognizant.user.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private AuthorizationClient authClient;

	@Test
	public void testGetUserType() throws Exception {
		String userName = "testUser";
		String password = "testPassword";
		String userType = "testUserType";

		when(userService.getUserType(userName, password)).thenReturn(userType);

		mockMvc.perform(get("/userType/{userName}/{password}", userName, password)).andExpect(status().isOk());
	}

	@Test
	void testGetUserId() throws Exception {
		int userId = 1;
		String userName = "testuser";
		String password = "testpassword";

		// configure the mock user service to return the user id
		when(userService.getUserId(userName, password)).thenReturn(userId);

		// make a GET request to the /userId/{userName}/{password} endpoint
		mockMvc.perform(MockMvcRequestBuilders.get("/userId/{userName}/{password}", userName, password)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testViewUserSuccess() throws Exception {
		int userId = 1;
		User user = new User(1, "username", "password", "password", "firstName", "lastName", "1234567890",
				"email@example.com", "userType");

		// Setup the mock behavior for the user service and authorization client
		when(authClient.validate("Bearer 1234567890")).thenReturn(true);
		when(userService.viewUser(userId)).thenReturn(user);

		// Perform the GET request
		mockMvc.perform(get("/user/{userId}", userId).header("Authorization", "Bearer 1234567890"))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testViewUserForbidden() throws Exception {
		int userId = 1;

		// Setup the mock behavior for the authorization client
		when(authClient.validate("Bearer 1234567890")).thenReturn(false);

		// Perform the GET request
		mockMvc.perform(get("/user/{userId}", userId).header("Authorization", "Bearer 1234567890"))
				.andExpect(status().isForbidden());
	}

	@Test
	public void testViewAllUsers_ShouldReturnOKStatus_WhenAuthorizationIsValid() throws Exception {
		// Given
		String token = "valid_token";
		List<User> users = Arrays.asList(new User(), new User());
		when(authClient.validate(token)).thenReturn(true);
		when(userService.viewAllUsers()).thenReturn(users);

		// When
		ResultActions result = mockMvc.perform(get("/users").header("Authorization", token));

		// Then
		result.andExpect(status().isOk());
		verify(authClient, times(1)).validate(token);
		verify(userService, times(1)).viewAllUsers();
	}

	@Test
	public void testViewAllUsers_ShouldReturnForbiddenStatus_WhenAuthorizationIsInvalid() throws Exception {
		// Given
		String token = "invalid_token";
		when(authClient.validate(token)).thenReturn(false);

		// When
		ResultActions result = mockMvc.perform(get("/users").header("Authorization", token));

		// Then
		result.andExpect(status().isForbidden());
		verify(authClient, times(1)).validate(token);
		verify(userService, times(0)).viewAllUsers();
	}

	@Test
	public void testViewAllTrainers_Success() throws Exception {
		List<User> trainers = new ArrayList<>();
		trainers.add(new User(1, "username", "password", "confirmPassword", "firstName", "lastName", "contactNumber",
				"emailId", "trainer"));
		trainers.add(new User(2, "username2", "password2", "confirmPassword2", "firstName2", "lastName2",
				"contactNumber2", "emailId2", "trainer"));

		when(userService.viewAllTrainers()).thenReturn(trainers);
		when(authClient.validate("test-token")).thenReturn(true);

		mockMvc.perform(get("/trainers").header("Authorization", "test-token")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].userId", is(1)))
				.andExpect(jsonPath("$[0].userName", is("username")))
				.andExpect(jsonPath("$[0].firstName", is("firstName")))
				.andExpect(jsonPath("$[0].lastName", is("lastName")))
				.andExpect(jsonPath("$[0].contactNumber", is("contactNumber")))
				.andExpect(jsonPath("$[0].emailId", is("emailId"))).andExpect(jsonPath("$[0].userType", is("trainer")))
				.andExpect(jsonPath("$[1].userId", is(2))).andExpect(jsonPath("$[1].userName", is("username2")))
				.andExpect(jsonPath("$[1].firstName", is("firstName2")))
				.andExpect(jsonPath("$[1].lastName", is("lastName2")))
				.andExpect(jsonPath("$[1].contactNumber", is("contactNumber2")))
				.andExpect(jsonPath("$[1].emailId", is("emailId2")))
				.andExpect(jsonPath("$[1].userType", is("trainer")));
	}

	@Test
	public void testViewAllTrainers_Forbidden() throws Exception {
		when(authClient.validate("test-token")).thenReturn(false);

		mockMvc.perform(get("/trainers").header("Authorization", "test-token")).andExpect(status().isForbidden());
	}
}
