package com.vikramr.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.vikramr.demo.services.password.PasswordValidatorService;
import com.vikramr.demo.util.PasswordUtil;

@SpringBootApplication
@ComponentScan("com.vikramr")
public class PasswordValidatorApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(PasswordValidatorApplication.class, args);
        
        PasswordValidatorService svc = ctx.getBean(PasswordValidatorService.class);
        svc.setPasswordPolicy(PasswordUtil.getDefaultPasswordPolicy());
        
        System.out.println("PasswordValidatorApplication started");
    }
}
