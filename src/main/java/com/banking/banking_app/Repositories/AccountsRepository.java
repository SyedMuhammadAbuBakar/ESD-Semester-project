package com.banking.banking_app.Repositories;

import com.banking.banking_app.Entities.Accounts;
import com.banking.banking_app.Entities.Customers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    List<Accounts> findByCustomers(Customers customer);
    List<Accounts> findByCustomers_CustomerName(Customers customer);    
}
