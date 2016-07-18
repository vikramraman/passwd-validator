package com.vikramr.demo.util;

import java.util.List;

/**
 * The interface describing the password policies to be enforced.
 * 
 * @author Vikram Raman
 */
public interface PasswordPolicy {
	
	/**
	 * Gets the list of all policies currently enforced.
	 * 
	 * @return the list of all policies currently enforced
	 */
	public List<String> getDescription();
	
	/**
	 * Adds the given validator for enforcement.
	 * 
	 * @param validator the validator to add
	 */
	public void addValidator(Validator<String> validator);
	
	/**
	 * Gets the list of validators enforced by this policy.
	 * 
	 * @return the list of string validators
	 */
	public List<Validator<String>> getValidators();
}
