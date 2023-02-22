package com.cognizant.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.gym.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
