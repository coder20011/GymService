
package com.cognizant.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.model.MyUser;
import com.cognizant.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
   
	@Autowired
	private UserRepo userRepo;

	/*Athira*/
	
	@Override
	@Transactional
	public void insertUser(MyUser user)  {
		userRepo.save(user);
	}
}
