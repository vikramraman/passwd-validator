package com.vikramr.demo.util;

import java.util.regex.Pattern;

/**
 * A string validator that applies a regular expression to determine
 * the validity of a string input.
 * 
 * @author Vikram Raman
 */
public class RegexValidator 
implements Validator<String> {
	
	/** the regular expression pattern to use. */
	private Pattern itsPattern;
	
	/** the error message if validity fails. */
	private String itsMsg;
	
	/** whether the given regular expression should be inverted. */
	private boolean itsInvert;

	/**
	 * Constructor.
	 * 
	 * @param regex the regular expression to use for validation
	 * @param msg the error message if validation fails
	 * @param invert true if the regular expression validation should be inverted
	 */
	public RegexValidator(String regex, String msg, boolean invert) {
		setRegex(regex);
		setErrorMessage(msg);
		setInversion(invert);
	}
	
	public void setRegex(String regex) {
		itsPattern = Pattern.compile(regex);
	}
	
	public void setErrorMessage(String msg) {
		itsMsg = msg;
	}
	
	public void setInversion(boolean invert) {
		itsInvert = invert;
	}
	
	@Override
	public boolean isValid(String input) {
		boolean exists = itsPattern.matcher(input).find();
		return itsInvert ? !exists : exists;
	}

	@Override
	public String getDescription() {
		return itsMsg;
	}
}
