package com.vikramr.demo.util;

import java.util.Properties;

/**
 * Utility class for managing passwords.
 * 
 * @author Vikram Raman
 */
public class PasswordUtil {
	
	/** the default minimum password length. */
	public static final int MIN_LENGTH = 5;
	
	/** the default maximum password length. */
	public static final int MAX_LENGTH = 12;
	
	/** the default password length validator. */
	public static final Validator<String> DEFAULT_LEN_VALIDATOR = 
		new StringLengthValidator(MIN_LENGTH, MAX_LENGTH);
	
	/** the regular expression to detect repeat characters. */
	public static final String REPEAT_REGEX = "(.+)\\1+";
	
	/** the default repeat validator description. */
	public static final String REPEAT_DESCR = 
		"Input should not contain any sequence of characters " + 
	    "immediately followed by the same sequence";
	
	/** the default repeat validator. */
	public static final Validator<String> DEFAULT_REPEAT_VALIDATOR = 
			new RegexValidator(REPEAT_REGEX, REPEAT_DESCR, true);
	
	/** the default list of allowed characters. */
	public static final String CHARS_REGEX = "^(?=.*\\d)(?=.*[a-z])[a-z0-9]*$";
	
	/** the default allowed characters description. */
	public static final String CHARS_DESCR = 
		"Input must contain only lowercase letters and numerical digits," +
	    " with at least one of each";
	
	/** the default allowed characters validator. */
	public static final Validator<String> DEFAULT_CHARS_VALIDATOR = 
			new RegexValidator(CHARS_REGEX, CHARS_DESCR, false);
	
	/**
	 * Gets the default password policy.
	 * 
	 * @return the default password policy
	 */
    public static PasswordPolicy getDefaultPasswordPolicy() {
    	
    	// Note: The list of validators can be extended to support
    	// internationalization etc.
    	PasswordPolicy policy = new PasswordPolicyImpl();
    	policy.addValidator(DEFAULT_LEN_VALIDATOR);
    	policy.addValidator(DEFAULT_CHARS_VALIDATOR);
    	policy.addValidator(DEFAULT_REPEAT_VALIDATOR);
    	return policy;
    }
    
    /**
     * Gets a password policy representing the given policy properties.
     * 
     * @param props the properties describing the password policy
     * @return the password policy representing the given properties
     */
    public static PasswordPolicy getPasswordPolicy(Properties props) {
    	// TODO: This method can be fleshed out to create policies
    	// that are driven by configuration files for ease of control
	    return null;
    }
}
