package com.cognizant.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.user.model.User;

@Service
public interface UserService {

	/* Labeeb */
	public List<User> viewAllUsers();

	public List<User> viewAllTrainers();

	/* Athira */
	public void registerUser(User user);

	public String getUserType(String userName, String password);

	/* Kiruthiga */
	public Integer getUserId(String userName, String password);

	public User viewUser(int userId);

}