package com.cognizant.gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.gym.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

	/* Kiruthiga */

	@Query(value = "select * from food where food_cat_id=?1", nativeQuery = true)
	public List<Food> findByCategory(Integer foodCatId);

}
