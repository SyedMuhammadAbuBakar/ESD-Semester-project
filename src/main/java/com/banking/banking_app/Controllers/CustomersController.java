package com.banking.banking_app.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.banking.banking_app.Entities.Customers;
import com.banking.banking_app.Services.CustomersService;
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
public class CustomersController {

    @Autowired
    CustomersService customersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
 
    
@PostMapping("/login")
public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getCustomerName(),
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


    @PostMapping("/signup")
    public Customers createCustomer(@RequestBody Customers customer) {

        String HashedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setHashedPassword(HashedPassword);
        return customersService.createCustomer(customer);

    }

    @GetMapping("/protected")
    public String protectedTest() {
    return "JWT works!";
    }

}
