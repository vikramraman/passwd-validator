package com.vikramr.demo.services.password;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vikramr.demo.util.PasswordPolicy;
import com.vikramr.demo.util.Validator;

/**
 * Service class for validating passwords.
 *  
 * @author Vikram Raman
 */
@Service
public class PasswordValidatorService {
	
	private PasswordPolicy passwordPolicy;

	/**
	 * Sets the password policy to be enforced by this validator service.
	 * 
	 * @param policy the password policy instance
	 */
	public void setPasswordPolicy(PasswordPolicy policy) {
		passwordPolicy = policy;
	}

	/**
	 * Validates the given password string.
	 * 
	 * @param password a password string
	 * @return A list of error strings if invalid, null if valid
	 */
	public List<String> validate(String password) {
		
		if (passwordPolicy == null || passwordPolicy.getValidators() == null) {
			return null;
		}
		
		List<String> errors = null;
		for (Validator<String> validator : passwordPolicy.getValidators()) {
			if (!validator.isValid(password)) {
				if (errors == null) {
					errors = new ArrayList<>();
				}
				errors.add(validator.getDescription());
			}
		}
		return errors;
	}
}
