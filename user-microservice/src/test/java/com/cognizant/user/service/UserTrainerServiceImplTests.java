package com.cognizant.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.cognizant.user.model.UserTrainer;
import com.cognizant.user.repository.UserRepository;
import com.cognizant.user.repository.UserTrainerRepository;

@ExtendWith(MockitoExtension.class)
public class UserTrainerServiceImplTests {

	@Mock
	private UserTrainerRepository userTrainerRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserTrainerServiceImpl userTrainerServiceImpl;

	@Test
	public void testAssignTrainer_Success() {
		UserTrainer userTrainer = new UserTrainer();
		userTrainer.setUserId(1);
		userTrainer.setTrainerId(2);

		when(userTrainerRepository.findByUserId(1)).thenReturn(null);

		String result = userTrainerServiceImpl.assignTrainer(userTrainer);

		assertEquals("Trainer with id 2 is assigned for User with id 1", result);
	}

	@Test
	public void testAssignTrainer_TrainerAlreadyAssigned() {
		UserTrainer userTrainer = new UserTrainer();
		userTrainer.setUserId(1);
		userTrainer.setTrainerId(2);

		when(userTrainerRepository.findByUserId(1)).thenReturn(new UserTrainer());

		String result = userTrainerServiceImpl.assignTrainer(userTrainer);

		assertEquals("Trainer is already assigned for User with id 1", result);
	}

	@Test
	public void testViewAssignedUsers_Success() throws UserNotFoundException {
		int trainerId = 1;
		List<Integer> userIds = new ArrayList<>();
		userIds.add(1);
		userIds.add(2);
		List<User> users = new ArrayList<>();
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("user1");
		users.add(user1);
		User user2 = new User();
		user2.setUserId(2);
		user2.setUserName("user2");
		users.add(user2);
		when(userTrainerRepository.findUserIdsByTrainerId(trainerId)).thenReturn(userIds);
		when(userRepository.findUsersByIdIn(userIds)).thenReturn(users);
		List<User> result = userTrainerServiceImpl.viewAssignedUsers(trainerId);
		assertEquals(users, result);
	}

	@Test
	public void testViewAssignedUsers_UserNotFoundException() throws UserNotFoundException {
		int trainerId = 1;
		List<Integer> userIds = new ArrayList<>();
		when(userTrainerRepository.findUserIdsByTrainerId(trainerId)).thenReturn(userIds);
		assertThrows(UserNotFoundException.class, () -> {
			userTrainerServiceImpl.viewAssignedUsers(trainerId);
		});
	}
}