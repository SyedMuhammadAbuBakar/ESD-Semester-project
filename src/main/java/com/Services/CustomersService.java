package com.banking.banking_app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.banking_app.Entities.Customers;
import com.banking.banking_app.Repositories.CustomersRepository;

@Service
public class CustomersService {

@Autowired
CustomersRepository customersRepository;

@SuppressWarnings("null")
public Customers createCustomer(Customers entity) {
    return customersRepository.save(entity);
}

}