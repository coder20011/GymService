package com.cognizant.gym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ApiModel(description = "Model class for Food")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id of the Food")
	private Integer foodId;
	@Column
	@ApiModelProperty(notes = "Name of the Food")
	@NotBlank(message = "Food name cannot be empty")
	private String foodName;
	@ManyToOne
	@JoinColumn(name = "food_cat_id")
	@ApiModelProperty(notes = "Category of the Food")
	@NotNull(message = "Category cannot be empty")
	private Category category;
	@Column
	@ApiModelProperty(notes = "Calories Per Quantity of the Food")
	@NotNull(message = "Calories Per Quantity cannot be empty")
	private Float calPerQty;
	@Column
	@ApiModelProperty(notes = "Units of measurement for the Food")
	@NotBlank(message = "Units cannot be empty")
	private String units;

}
