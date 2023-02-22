package com.cognizant.user.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.user.model.UserTrainer;

@Repository
public interface UserTrainerRepository extends JpaRepository<UserTrainer, Integer> {

	/*Athira*/
	public UserTrainer findByUserId(int userId);

	/* Labeeb */
	@Query(value = "SELECT ut.user_id FROM USER_TRAINER ut WHERE ut.trainer_id = ?1", nativeQuery = true)
	public List<Integer> findUserIdsByTrainerId(int trainer_id);	

}