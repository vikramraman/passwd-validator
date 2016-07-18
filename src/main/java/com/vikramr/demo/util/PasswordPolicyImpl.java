package com.vikramr.demo.util;

import java.util.ArrayList;
import java.util.List;


/**
 * This class encapsulates the password policies to be enforced. 
 * 
 * @author Vikram Raman
 */
public class PasswordPolicyImpl 
implements PasswordPolicy {

	private List<Validator<String>> validators = new ArrayList<>();

	@Override
	public List<Validator<String>> getValidators() {
		return validators;
	}

	@Override
	public void addValidator(Validator<String> validator) {
		validators.add(validator);
	}

	@Override
	public List<String> getDescription() {
		List<String> descr = new ArrayList<>();
		for (Validator<String> val : validators) {
			descr.add(val.getDescription());
		}
		return descr;
	}
}
