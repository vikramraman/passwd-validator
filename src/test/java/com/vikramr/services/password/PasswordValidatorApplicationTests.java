package com.vikramr.services.password;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vikramr.demo.PasswordValidatorApplication;
import com.vikramr.demo.services.password.PasswordValidatorService;
import com.vikramr.demo.util.PasswordPolicy;
import com.vikramr.demo.util.PasswordPolicyImpl;
import com.vikramr.demo.util.PasswordUtil;
import com.vikramr.demo.util.StringLengthValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PasswordValidatorApplication.class)
@WebAppConfiguration
public class PasswordValidatorApplicationTests {

	PasswordValidatorService service;
	
	@Before
	public void setUp() throws Exception {
		service = new PasswordValidatorService();
	}

	@Test
	public void testPasswordLength() {
		
		// create a policy with just length validation
		PasswordPolicy policy = new PasswordPolicyImpl();
		StringLengthValidator validator = new StringLengthValidator(5, 12);
		policy.addValidator(validator);
		service.setPasswordPolicy(policy);

		List<String> errors = null;
		
		// less than min length
		errors = service.validate("foo1");
		assertNotNull(errors);
		assertEquals(errors.size(), 1);
		
		// same as min length
		errors = service.validate("foo12");
		assertNull(errors);
		
		// between min and max
		errors = service.validate("foobar1234");
		assertNull(errors);
		
		// same as max length
		errors = service.validate("foobar123456");
		assertNull(errors);

		// greater than max
		errors = service.validate("foobar12345678");
		assertNotNull(errors);
		assertEquals(errors.size(), 1);
	}
	
	@Test
	public void testPasswordCharacters() {
		// create a policy with just allowed characters validation
		PasswordPolicy policy = new PasswordPolicyImpl();
		policy.addValidator(PasswordUtil.DEFAULT_CHARS_VALIDATOR);
		service.setPasswordPolicy(policy);
		
		List<String> errors = null;
		
		// no digits
		errors = service.validate("foobar");
		assertNotNull(errors);
		assertEquals(errors.size(), 1);
		
		// no lowercase
		errors = service.validate("12345");
		assertNotNull(errors);
		assertEquals(errors.size(), 1);
		
		// lowercase and digits
		errors = service.validate("foo123");
		assertNull(errors);
		
		// uppercase and digits
		errors = service.validate("FOO123");
		assertNotNull(errors);
		assertEquals(errors.size(), 1);
		
		// lowercase and digits, and upper case
		errors = service.validate("foo123ABC");
		assertNotNull(errors);
		assertEquals(errors.size(), 1);
	}
	
	@Test
	public void testPasswordRepeat() {
		// create a policy with just repeated characters validation
		PasswordPolicy policy = new PasswordPolicyImpl();
		policy.addValidator(PasswordUtil.DEFAULT_REPEAT_VALIDATOR);
		service.setPasswordPolicy(policy);
		
		List<String> errors = null;
		
		// no repeat
		errors = service.validate("test1234");
		assertNull(errors);
		
		// repeat single character
		errors = service.validate("foobar");
		assertNotNull(errors);
		assertEquals(errors.size(), 1);
		
		// repeat word
		errors = service.validate("testtest1234");
		assertNotNull(errors);
		assertEquals(errors.size(), 1);
		
		// repeat word but not immediately
		errors = service.validate("test1234test");
		assertNull(errors);
		
		// repeat single digit
		errors = service.validate("test11");
		assertNotNull(errors);
		assertEquals(errors.size(), 1);
		
		// repeat digits
		errors = service.validate("test1234234");
		assertNotNull(errors);
		assertEquals(errors.size(), 1);
		
		// repeat words and digits
		errors = service.validate("test101test101");
		assertNotNull(errors);
		assertEquals(errors.size(), 1);
	}
}
