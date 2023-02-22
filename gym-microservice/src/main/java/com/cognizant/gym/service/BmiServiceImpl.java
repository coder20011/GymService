package com.cognizant.gym.service;

import java.text.DecimalFormat;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BmiServiceImpl implements BmiService {

	/*Vamsee*/
	@Override
	@Transactional
	public String calculateBmi(float weight, float height) {

		log.info("Calculating BMI");

		String result = "";

		float bmi = weight / (height * height);

		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		// Using decimal format to convert 7 digit precision to 1 digit precision.

		String bmiValue = decimalFormat.format(bmi);
		if (bmi <= 18.5) {
			result += "Your BMI Score is " + bmiValue + " and your BMI Status is Underweight";
		} else if (bmi > 18.5 && bmi <= 25.0) {
			result += "Your BMI Score is " + bmiValue + " and your BMI Status is Normal weight";
		} else if (bmi > 25.0 && bmi <= 30.0) {
			result += "Your BMI Score is " + bmiValue + " and your BMI Status is OverWeight";
		} else if (bmi > 30.0 && bmi <= 35.0) {
			result += "Your BMI score is " + bmiValue + " and your BMI Status is Obese";
		} else {
			result += "Your BMI score is " + bmiValue + " and your BMI Status is Extremely Obese";
		}

		return result;

	}

}