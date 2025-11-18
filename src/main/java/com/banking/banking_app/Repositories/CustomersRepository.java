package com.banking.banking_app.Repositories;

import com.banking.banking_app.Entities.Customers;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
///import java.util.List;


@Repository
public interface CustomersRepository extends JpaRepository<Customers,Long> {
Optional<Customers>findByCustomerName(String CustomerName);
}
