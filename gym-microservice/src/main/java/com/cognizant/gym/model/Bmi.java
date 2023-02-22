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
@ApiModel(description = "Model Class for BMI")
public class Bmi {

	@NotBlank(message = "Height cannot be empty")
	@ApiModelProperty(notes = "Height of the user")
	private float height;
	@NotBlank(message = "Weight cannot be empty")
	@ApiModelProperty(notes = "Weight of the user")
	private float weight;

}