package com.banking.banking_app.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.banking.banking_app.Entities.Employees;
import com.banking.banking_app.Services.EmployeesService;
import com.banking.banking_app.DTO.LoginRequest;
import com.banking.banking_app.DTO.LoginResponse;
import com.banking.banking_app.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // Employee Signup
    @PostMapping("/employee/signup")
    public Employees createEmployee(@RequestBody Employees employee) {
        String hashedPassword = passwordEncoder.encode(employee.getHashedPassword());
        employee.setHashedPassword(hashedPassword);
        return employeesService.createEmployee(employee);
    }

    // Employee Login
    @PostMapping("/employee/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmployeeName(), 
                    loginRequest.getPassword()
                )
            );

            String jwt = jwtUtil.generateToken(loginRequest.getCustomerName());

            LoginResponse response = new LoginResponse();
            response.setToken(jwt);
            response.setUsername(loginRequest.getCustomerName());

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    // Protected route
    @GetMapping("/employee/protected")
    public String protectedTest() {
        return "Employee JWT works!";
    }
}
