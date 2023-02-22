package com.cognizant.gym.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.gym.exceptions.CategoryNotFoundException;
import com.cognizant.gym.model.Category;
import com.cognizant.gym.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTests {

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryServiceImpl categoryService;

	private List<Category> categoryList;

	@BeforeEach
	public void setUp() {
		categoryList = new ArrayList<>();
		categoryList.add(new Category(1, "Fruits", null));
		categoryList.add(new Category(2, "Vegetables", null));
	}

	@Test
	public void testGetAllCategory() throws CategoryNotFoundException {
	    when(categoryRepository.findAll()).thenReturn(categoryList);
	    List<Category> result = categoryService.getAllCategory();
	    assertEquals(categoryList, result);
	}

	@Test
	public void testGetAllCategoryNoCategoryFound() throws CategoryNotFoundException {
		when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
		try {
			categoryService.getAllCategory();
		} catch (CategoryNotFoundException e) {
			assertEquals("Database is empty", e.getMessage());
		}
	}

	@Test
	public void testGetCategory() {
		Integer categoryId = 1;
		Category expectedCategory = new Category(1, "Fruits", null);
		when(categoryRepository.findById(categoryId)).thenReturn(java.util.Optional.of(expectedCategory));
		Category actualCategory = categoryService.getCategory(categoryId);
		assertEquals(expectedCategory, actualCategory);
	}
	
}
