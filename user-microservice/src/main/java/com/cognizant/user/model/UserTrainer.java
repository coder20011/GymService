package com.cognizant.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel(description = "Model class of the User Trainer")
public class UserTrainer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Transaction Id")
	private int transactionId;
	@Column
	@ApiModelProperty(notes = "Id of the user")
	@NotNull(message = "User Id cannot be empty")
	private int userId;
	@Column
	@ApiModelProperty(notes = "Trainer Id of the trainer")
	@NotNull(message = "Trainer Id cannot be empty")
	private int trainerId;

}