package com.cognizant.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.cognizant.model.MyUser;
import com.cognizant.repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * Service implementation class for UserDetailsService
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	/*Kiruthiga*/
	/**
	 * Overriding method to load the user details from database with user name
	 * 
	 * @param userName
	 * @return This returns the user name and password
	 */
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		MyUser user = userRepo.findByUserName(username);
		log.info("Loading the user details from database with user name");
		return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}

}
