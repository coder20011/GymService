package com.cognizant.user.model;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(description = "Model class of User")
public class User {
	
	@Id
	@ApiModelProperty(notes = "Id of the user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column
	@ApiModelProperty(notes = "Name of the user")
	@NotBlank(message = "Username cannot be empty")
	private String userName;
	@Column
	@ApiModelProperty(notes = "Password of the user")
	@NotBlank(message = "Password cannot be empty")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must contain alphanumeric characters and be at least 8 characters long")
	private String password;
	@Column
	@ApiModelProperty(notes = "Confirm password")
	@NotBlank(message = "Password cannot be empty")
	private String confirmPassword;
	@Column
	@ApiModelProperty(notes = "First name of the user")
	@NotBlank(message = "First name cannot be empty")
	private String firstName;
	@Column
	@ApiModelProperty(notes = "Last name of the user")
	@NotBlank(message = "Last name cannot be empty")
	private String lastName;
	@Column
	@ApiModelProperty(notes = "Phone number of the user")
	@NotBlank(message = "Phone number cannot be empty")
	@Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")
	private String contactNumber;
	@Column
	@ApiModelProperty(notes = "Email Id of the user")
	@NotBlank(message = "Email Id cannot be empty")
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String emailId;
	@Column
	@ApiModelProperty(notes = "Type of the user")
	private String userType;

}
