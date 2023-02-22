package com.cognizant.gym.model;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Model class for Calories")
public class Calories {

	@ApiModelProperty(notes = "Id of the food")
	@NotBlank(message = "Food Id cannot be empty")
	private Integer foodId;
	@ApiModelProperty(notes = "Quantity of the food item as input by user")
	@NotBlank(message = "Quantity cannot be empty")
	private Float quantity;

}
