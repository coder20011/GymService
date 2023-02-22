package com.cognizant.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.model.MyUser;
import com.cognizant.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(MyUserController.class)
public class MyUserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImpl userService;

	@Test
	public void testAddUser() throws Exception {
		MyUser user = new MyUser();
		user.setUserName("test");
		user.setPassword("password");

		doNothing().when(userService).insertUser(user);

		mockMvc.perform(post("/user/test/password")).andExpect(status().isCreated())
				.andExpect(content().string("Inserted"));

		verify(userService, times(1)).insertUser(user);
	}
}
