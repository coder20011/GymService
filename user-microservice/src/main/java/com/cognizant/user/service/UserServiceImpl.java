package com.cognizant.user.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.user.exceptions.UserNotFoundException;
import com.cognizant.user.model.User;
import com.cognizant.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/* Labeeb */
	@Override
	@Transactional
	public List<User> viewAllUsers() throws UserNotFoundException {
		log.info("Getting All Users");
		List<User> userList = userRepository.getAllUsersByType("User");
		if (userList.size() == 0) {
			log.info("Exception occurs");
			throw new UserNotFoundException("Database is empty");
		} else {
			return userList;
		}
	}

	@Override
	@Transactional
	public List<User> viewAllTrainers() throws UserNotFoundException {
		log.info("Getting All Trainers");
		List<User> userList = userRepository.getAllUsersByType("Trainer");
		if (userList.size() == 0) {
			log.info("Exception occurs");
			throw new UserNotFoundException("Database is empty");
		} else {
			return userList;
		}
	}

	/* Athira */
	@Override
	@Transactional
	public void registerUser(User user) {
		log.info("User is registered");
		userRepository.save(user);
	}

	@Override
	@Transactional
	public String getUserType(String userName, String password) {
		log.info("Getting the user type");
		return userRepository.getUserType(userName, password);
	}

	/* Kiruthiga */
	@Override
	@Transactional
	public Integer getUserId(String userName, String password) {
		log.info("Getting the user id");
		return userRepository.getUserId(userName, password);
	}

	@Override
	@Transactional
	public User viewUser(int userId) {
		log.info("Getting the user by id");
		return userRepository.findByUserId(userId);
	}

}