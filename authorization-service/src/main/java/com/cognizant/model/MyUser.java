package com.cognizant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for storing user data in database
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ApiModel(description = "Model class for MyUser")
public class MyUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@ApiModelProperty(notes = "Id of the user")
	private int userId;

	@Column
	@NotBlank(message = "User name cannot be empty")
	@ApiModelProperty(notes = "UserName of the user")
	private String userName;

	@Column
	@NotBlank(message = "Password cannot be blank")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must contain alphanumeric characters and be at least 8 characters long")
	@ApiModelProperty(notes = "Password of the user")
	private String password;

}
