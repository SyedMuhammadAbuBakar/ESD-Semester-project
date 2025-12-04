package com.banking.banking_app.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.banking.banking_app.Repositories.CustomersRepository;
import com.banking.banking_app.Repositories.EmployeesRepository;
import com.banking.banking_app.Entities.Customers;
import com.banking.banking_app.Entities.Employees;

import java.util.Optional;
import org.springframework.security.core.userdetails.User;

@Service
public class RetrieveUserDetailsService implements UserDetailsService {

    private final CustomersRepository customerRepo;
    private final EmployeesRepository employeeRepo;

    public RetrieveUserDetailsService(CustomersRepository customerRepo,
                                      EmployeesRepository employeeRepo) {
        this.customerRepo = customerRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1. Customer login
        Optional<Customers> customer = customerRepo.findByCustomerName(username);
        if (customer.isPresent()) {
            return User.withUsername(customer.get().getCustomerName())
                    .password(customer.get().getHashedPassword())
                    .authorities("CUSTOMER")
                    .build();
        }

        // 2. Employee login
        Optional<Employees> employee = employeeRepo.findByEmployeeName(username);
        if (employee.isPresent()) {
            return User.withUsername(employee.get().getEmployeeName())
                    .password(employee.get().getHashedPassword())
                    .authorities("EMPLOYEE")
                    .build();
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}
