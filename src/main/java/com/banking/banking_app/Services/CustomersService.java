package com.banking.banking_app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.banking_app.Entities.Customers;
import com.banking.banking_app.Repositories.CustomersRepository;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository customersRepository;

    // create customer
    public Customers createCustomer(Customers entity) {
        return customersRepository.save(entity);
    }

    // find customer by username
    public Customers getByCustomerName(String name) {
        return customersRepository.findByCustomerName(name)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + name));
    }


    public Customers getCustomerById(Long id) {
        return customersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }
}
