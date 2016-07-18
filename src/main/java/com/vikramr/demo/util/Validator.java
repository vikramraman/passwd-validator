package com.vikramr.demo.util;

/**
 * A generic interface for validating inputs.
 * 
 * @author Vikram Raman
 */
public interface Validator<T> {
	
    public boolean isValid(T input);
    
    public String getDescription();
}
