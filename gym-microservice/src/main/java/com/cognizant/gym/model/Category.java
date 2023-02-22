package com.cognizant.gym.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ApiModel(description = "Model class for Category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id of the Category")
	private Integer categoryId;
	@Column
	@ApiModelProperty(notes = "Name of the Category")
	@NotBlank(message = "Category name cannot be empty")
	private String categoryName;
	@OneToMany(mappedBy = "category")
	@JsonBackReference
	@ApiModelProperty(notes = "List of Foods for a particular Category")
	private Set<Food> foodList;

}
