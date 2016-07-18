package com.vikramr.demo.services.password;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class PasswordValidationController {
	
	/** the service for validating passwords. */
	private final PasswordValidatorService validatorService;
	
	@Autowired
	public PasswordValidationController(PasswordValidatorService service) {
		validatorService = service;
	}
	
	@RequestMapping(value="/validate", method = RequestMethod.POST)
    public ResponseEntity<String> validate(
    		@RequestParam(value="password", required=true) char[] passchars) {
		
		// It is assumed the password is sent over HTTPS.
		String password = new String(passchars);
		
		if (password == null || password.isEmpty()) {
			return new ResponseEntity<String>(
				"Password cannot be empty", HttpStatus.BAD_REQUEST);
		}
		
		List<String> errors = validatorService.validate(password);
		if (errors == null || errors.isEmpty()) {
			return new ResponseEntity<String>(
				"Validation successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(
				getError(errors), HttpStatus.BAD_REQUEST);
		}
    }
	
	private String getError(List<String> errors) {
		StringBuilder sb = new StringBuilder();
		for (String error : errors) {
			sb.append(error + "\n");
		}
		return sb.toString();
	}
}
