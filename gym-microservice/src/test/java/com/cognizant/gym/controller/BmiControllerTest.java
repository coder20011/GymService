package com.cognizant.gym.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.gym.feign.AuthorizationClient;
import com.cognizant.gym.model.Bmi;
import com.cognizant.gym.service.BmiService;

@RunWith(SpringRunner.class)
@WebMvcTest(BmiController.class)
public class BmiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BmiService bmiService;

	@MockBean
	private AuthorizationClient authorizationClient;

	@Test
	public void testCalculateBmiSuccess() throws Exception {
		Bmi bmi = new Bmi(75.0f, 1.75f);
		when(authorizationClient.validate("test-token")).thenReturn(true);
		when(bmiService.calculateBmi(75.0f, 1.75f)).thenReturn("25.0");

		mockMvc.perform(MockMvcRequestBuilders.post("/bmi")
				.header("Authorization", "test-token")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"weight\":75,\"height\":1.75}"))
				.andExpect(status().isAccepted());
	}

	@Test
	public void testCalculateBmiForbidden() throws Exception {
		Bmi bmi = new Bmi(75.0f, 1.75f);
		when(authorizationClient.validate("test-token")).thenReturn(false);

		mockMvc.perform(MockMvcRequestBuilders.post("/bmi")
				.header("Authorization", "test-token")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"weight\":75,\"height\":1.75}"))
				.andExpect(status().isForbidden());
	}
}
