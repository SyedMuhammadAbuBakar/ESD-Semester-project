package com.banking.banking_app.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.banking.banking_app.Entities.Customers;
import com.banking.banking_app.Repositories.CustomersRepository;

@Service
public class RetrieveUserDetailsService implements UserDetailsService {

    private final CustomersRepository customerRepository;

        public RetrieveUserDetailsService(CustomersRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
 @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Customers customer = customerRepository.findByCustomerName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

    return org.springframework.security.core.userdetails.User
            .withUsername(customer.getCustomerName())
            .password(customer.getHashedPassword())  // hashed password in DB
            .roles("USER") // or roles from your DB
            .build();
    }
}

