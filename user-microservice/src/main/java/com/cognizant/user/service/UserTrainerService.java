package com.cognizant.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.user.model.User;
import com.cognizant.user.model.UserTrainer;

@Service
public interface UserTrainerService {

	/* Labeeb */
	public List<User> viewAssignedUsers(int trainerId);
	
	/*Athira*/
	public User getAssignedTrainer(int userId) ;

	/* Vamsee */
	public String assignTrainer(UserTrainer userTrainer);

}