package com.banking.banking_app.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.banking.banking_app.Entities.Customers;
import com.banking.banking_app.Services.CustomersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
///import org.springframework.boot.autoconfigure.AutoConfiguration;
///import org.springframework.web.bind.annotation.GetMapping;
///import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
///import com.banking.banking_app.security.ApplicationSecurityConfig;
import com.banking.banking_app.DTO.LoginRequest;
import org.springframework.security.core.Authentication;

@RestController
public class CustomersController {

@Autowired
CustomersService customersService;

@Autowired
private PasswordEncoder passwordEncoder;

@Autowired
private AuthenticationManager authenticationManager;


@PostMapping("/login")
public String login(@RequestBody LoginRequest loginRequest) {
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getCustomerName(),
                loginRequest.getPassword()
            )
        );

        if (authentication.isAuthenticated()) {
            return "Login successful! Welcome " + loginRequest.getCustomerName();
        } 
     } catch (BadCredentialsException e) {
            return " Invalid username or password!";
        }
    return "Authentication failed!";
    }


@PostMapping("/signup")
public Customers createCustomer(@RequestBody Customers customer) {
   
    String HashedPassword = passwordEncoder.encode(customer.getPassword());
    customer.setHashedPassword(HashedPassword);
    return customersService.createCustomer(customer);
    
}


    
}
