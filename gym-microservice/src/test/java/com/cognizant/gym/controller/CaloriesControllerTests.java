package com.cognizant.gym.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.gym.feign.AuthorizationClient;
import com.cognizant.gym.model.Calories;
import com.cognizant.gym.service.CaloriesService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CaloriesController.class)
public class CaloriesControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CaloriesService caloriesService;

	@MockBean
	private AuthorizationClient authorizationClient;

	@Test
	public void testCalculateTotalCalories() throws Exception {
		when(authorizationClient.validate(any())).thenReturn(true);
		when(caloriesService.calculateTotalCalories(any())).thenReturn(2000f);

		List<Calories> caloriesList = new ArrayList<>();
		caloriesList.add(new Calories(1, 500f));
		caloriesList.add(new Calories(2, 700f));
		caloriesList.add(new Calories(3, 800f));

		ObjectMapper mapper = new ObjectMapper();
		String requestJson = mapper.writeValueAsString(caloriesList);

		mockMvc.perform(post("/calories").header("Authorization", "test-token")
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(status().isAccepted())
				.andExpect(content().string("Your total Calories is 2000.0"));
	}
}
