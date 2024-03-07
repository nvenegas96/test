/*package com.test.sentra.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.sentra.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByEmailValidation implements ConstraintValidator<ExistByEmail, String> {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return !userService.existByEmail(email);
	}

}*/
