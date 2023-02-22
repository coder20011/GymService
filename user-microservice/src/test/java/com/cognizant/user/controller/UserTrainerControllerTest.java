package com.cognizant.user.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.cognizant.user.feign.AuthorizationClient;
import com.cognizant.user.model.User;
import com.cognizant.user.model.UserTrainer;
import com.cognizant.user.repository.UserTrainerRepository;
import com.cognizant.user.service.UserTrainerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserTrainerController.class)
public class UserTrainerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserTrainerRepository userTrainerRepository;

	@MockBean
	private UserTrainerServiceImpl userTrainerService;

	@MockBean
	private AuthorizationClient authClient;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void viewAssignedTrainerTestPositive() throws Exception {
		User trainer = new User();
		trainer.setUserId(1);
		trainer.setFirstName("John");
		trainer.setLastName("Doe");
		trainer.setUserType("Trainer");

		when(userTrainerService.getAssignedTrainer(1)).thenReturn(trainer);
		when(authClient.validate("AuthorizationToken")).thenReturn(true);

		mockMvc.perform(get("/trainer/1").header("Authorization", "AuthorizationToken")).andExpect(status().isOk())
				.andExpect(jsonPath("$.userId").value(1)).andExpect(jsonPath("$.firstName").value("John"))
				.andExpect(jsonPath("$.lastName").value("Doe")).andExpect(jsonPath("$.userType").value("Trainer"));

		verify(userTrainerService, times(1)).getAssignedTrainer(1);
		verify(authClient, times(1)).validate("AuthorizationToken");
	}

	@Test
    public void viewAssignedTrainerTestNegative() throws Exception {
        when(authClient.validate("AuthorizationToken")).thenReturn(false);
        
        mockMvc.perform(get("/trainer/1").header("Authorization", "AuthorizationToken"))
                .andExpect(status().isForbidden());
        
        verify(authClient, times(1)).validate("AuthorizationToken");
    }

	@Test
	void testViewAssignedUsers() throws Exception {
		// mock the User data
		User user1 = new User(1, "user1", "password", "password", "firstName1", "lastName1", "contactNumber1",
				"emailId1", "userType1");
		User user2 = new User(2, "user2", "password", "password", "firstName2", "lastName2", "contactNumber2",
				"emailId2", "userType2");
		List<User> users = Arrays.asList(user1, user2);

		// mock the response from userTrainerService
		when(userTrainerService.viewAssignedUsers(1)).thenReturn(users);

		// mock the response from authClient
		when(authClient.validate("token")).thenReturn(true);

		// perform the GET request to "/users/1" with a valid token
		mockMvc.perform(get("/users/1").header("Authorization", "token").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void testAssignTrainer_WhenAuthorizationIsValid_ShouldReturnAccepted() throws Exception {
		UserTrainer userTrainer = new UserTrainer(1, 1, 2);
		String json = mapper.writeValueAsString(userTrainer);
		when(authClient.validate(anyString())).thenReturn(true);
		when(userTrainerService.assignTrainer(userTrainer)).thenReturn("Trainer assigned successfully");

		mockMvc.perform(post("/assign").header("Authorization", "valid-token").contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(status().isAccepted())
				.andExpect(content().string("Trainer assigned successfully"));

		verify(authClient, times(1)).validate(anyString());
		verify(userTrainerService, times(1)).assignTrainer(userTrainer);
	}

	@Test
	public void testAssignTrainer_WhenAuthorizationIsInvalid_ShouldReturnForbidden() throws Exception {
		UserTrainer userTrainer = new UserTrainer(1, 1, 2);
		String json = mapper.writeValueAsString(userTrainer);
		when(authClient.validate(anyString())).thenReturn(false);

		mockMvc.perform(post("/assign").header("Authorization", "invalid-token").contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(status().isForbidden());

		verify(authClient, times(1)).validate(anyString());
		verify(userTrainerService, never()).assignTrainer(userTrainer);
	}
}
