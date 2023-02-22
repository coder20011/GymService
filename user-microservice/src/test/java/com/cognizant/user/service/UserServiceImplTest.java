package com.cognizant.user.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.user.exceptions.UserNotFoundException;
import com.cognizant.user.model.User;
import com.cognizant.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Test
	public void testViewAllUsers_Success() throws UserNotFoundException {
		List<User> expectedUserList = new ArrayList<>();
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("user1");
		user1.setUserType("User");
		expectedUserList.add(user1);

		User user2 = new User();
		user2.setUserId(2);
		user2.setUserName("user2");
		user2.setUserType("User");
		expectedUserList.add(user2);

		when(userRepository.getAllUsersByType("User")).thenReturn(expectedUserList);

		List<User> actualUserList = userService.viewAllUsers();

		assertEquals(expectedUserList, actualUserList);
	}

	@Test
  public void testViewAllUsers_UserNotFoundException() {
    when(userRepository.getAllUsersByType("User")).thenReturn(new ArrayList<>());

    assertThrows(UserNotFoundException.class, () -> {
      userService.viewAllUsers();
    });
  }

	@Test
	public void testViewAllTrainers_Success() throws UserNotFoundException {
		List<User> expectedUserList = new ArrayList<>();
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("trainer1");
		user1.setUserType("Trainer");
		expectedUserList.add(user1);

		User user2 = new User();
		user2.setUserId(2);
		user2.setUserName("Trainer2");
		user2.setUserType("Trainer");
		expectedUserList.add(user2);

		when(userRepository.getAllUsersByType("Trainer")).thenReturn(expectedUserList);

		List<User> actualUserList = userService.viewAllTrainers();

		assertEquals(expectedUserList, actualUserList);
	}

	@Test
  public void testViewAllTrainers_UserNotFoundException() {
    when(userRepository.getAllUsersByType("Trainer")).thenReturn(new ArrayList<>());

    assertThrows(UserNotFoundException.class, () -> {
      userService.viewAllTrainers();
    });
  }

	@Test
	public void testRegisterUser() {
		User user = new User();
		userService.registerUser(user);
	}

	@Test
	public void testGetUserType() {
		when(userRepository.getUserType(eq("user1"), eq("password1"))).thenReturn("User");
		String userType = userService.getUserType("user1", "password1");
		assertEquals("User", userType);
	}

	@Test
	public void testGetUserId() {
		when(userRepository.getUserId(eq("user1"), eq("password1"))).thenReturn(1);
		Integer userId = userService.getUserId("user1", "password1");
		assertEquals(1, userId);
	}

	@Test
	public void testViewUser() {
		User user = new User();
		when(userRepository.findByUserId(eq(1))).thenReturn(user);
		User result = userService.viewUser(1);
		assertNotNull(result);
	}
}