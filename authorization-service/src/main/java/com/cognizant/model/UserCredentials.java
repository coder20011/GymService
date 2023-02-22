package com.cognizant.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for fetching user credentials while login
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Model class for UserCredentials")
public class UserCredentials {

	@NotBlank(message = "UserName cannit be empty")
	@ApiModelProperty("userName of the user")
	private String userName;

	@NotBlank(message = "Password cannit be empty")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "Password should contain alpha numeric values")
	@ApiModelProperty("password of the user")
	private String password;
}
