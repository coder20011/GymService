package com.cognizant.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/*Athira*/
	
	@Query(value="Select user_type from user where user_name=?1 and password=?2",nativeQuery=true)
	public String getUserType(String userName,String password);
	
	public  User findByUserId(int id);

	/* Labeeb */
	@Query(value = "SELECT * from USER u where u.user_type = ?1", nativeQuery = true)
	public List<User> getAllUsersByType(String userType);
	
	@Query("SELECT u FROM User u WHERE u.id IN :user_id")
	public List<User> findUsersByIdIn(@Param("user_id") List<Integer> user_id);

	/* Kiruthiga */
	@Query(value = "Select user_id from user where user_name=?1 and password=?2", nativeQuery = true)
	public Integer getUserId(String userName, String password);

}