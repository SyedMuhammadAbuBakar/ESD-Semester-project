package com.banking.banking_app.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.banking_app.Entities.Loan;
import com.banking.banking_app.Entities.Customers;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByCustomer(Customers customer);
}
