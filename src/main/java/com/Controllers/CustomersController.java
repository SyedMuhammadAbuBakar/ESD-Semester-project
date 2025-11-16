package com.banking.banking_app.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.banking.banking_app.Entities.Customers;
import com.banking.banking_app.Services.CustomersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
///import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
///import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
///import com.banking.banking_app.security.ApplicationSecurityConfig;

@RestController
public class CustomersController {

@Autowired
CustomersService customersService;

@Autowired
private PasswordEncoder passwordEncoder;

@GetMapping("/login")
public String getMethodName() {
    return "Hello AbuBakar";
}

@PostMapping("/signup")
public Customers createCustomer(@RequestBody Customers customer) {
   
    String HashedPassword = passwordEncoder.encode(customer.getPassword());
    customer.setHashedPassword(HashedPassword);
    return customersService.createCustomer(customer);
    
}


    
}
