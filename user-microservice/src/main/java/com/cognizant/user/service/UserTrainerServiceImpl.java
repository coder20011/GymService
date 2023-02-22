package com.cognizant.user.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.user.exceptions.UserNotFoundException;
import com.cognizant.user.model.User;
import com.cognizant.user.model.UserTrainer;
import com.cognizant.user.repository.UserRepository;
import com.cognizant.user.repository.UserTrainerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserTrainerServiceImpl implements UserTrainerService {

	@Autowired
	private UserTrainerRepository userTrainerRepository;

	@Autowired
	private UserRepository userRepository;

	/* Vamsee */
	@Override
	@Transactional
	public String assignTrainer(UserTrainer userTrainer) {
		String status = "";
		UserTrainer userTrainer1 = userTrainerRepository.findByUserId(userTrainer.getUserId());
		if (userTrainer1 == null) {
			log.info("Trainer is assigned");
			userTrainerRepository.save(userTrainer);
			status = "Trainer with id " + userTrainer.getTrainerId() + " is assigned for User with id "
					+ userTrainer.getUserId();
		} else {
			status = "Trainer is already assigned for User with id " + userTrainer.getUserId();
		}
		return status;
	}

	/* Labeeb */

	@Override
	@Transactional
	public List<User> viewAssignedUsers(int trainerId) throws UserNotFoundException {
		List<Integer> user_id = userTrainerRepository.findUserIdsByTrainerId(trainerId);
		if (user_id.size() == 0) {
			log.info("Exception occurs");
			throw new UserNotFoundException("No users assigned for this trainer with id " + trainerId);
		}
		List<User> users = userRepository.findUsersByIdIn(user_id);
		return users;

	}

	/* Athira */

	@Override
	@Transactional
	public User getAssignedTrainer(int userId) throws UserNotFoundException {
		UserTrainer userTrainer = userTrainerRepository.findByUserId(userId);
		if (userTrainer == null) {
			log.info("Exception occurs");
			throw new UserNotFoundException("Trainer not assigned for the user with id " + userId);
		} else {
			User trainer = userRepository.findByUserId(userTrainer.getTrainerId());
			return trainer;
		}
	}

}
