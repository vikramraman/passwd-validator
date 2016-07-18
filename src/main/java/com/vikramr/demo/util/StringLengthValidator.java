package com.vikramr.demo.util;

/**
 * A string validator that merely checks the length of a given input string
 * is within the configured bounds.
 * 
 * @author Vikram Raman
 */
public class StringLengthValidator 
implements Validator<String> {
	
	/** the minimum string length. */
	int min;
	
	/** the maximum string length. */
	int max;
	
	/** the error message. */
	private static final String MSG = 
			"Input should be between %d and %d characters";
	
	public StringLengthValidator() {
	}
	
	public StringLengthValidator(int min, int max) {
		setMinimum(min);
		setMaximum(max);
	}
	
	public void setMinimum(int min) {
		this.min = min;
	}
	
	public void setMaximum(int max) {
		this.max = max;
	}

	@Override
	public boolean isValid(String input) {
		int len = (input == null) ? 0 : input.length();
		return len >= min && len <= max;
	}

	@Override
	public String getDescription() {
		return String.format(MSG, min, max);
	}
}
