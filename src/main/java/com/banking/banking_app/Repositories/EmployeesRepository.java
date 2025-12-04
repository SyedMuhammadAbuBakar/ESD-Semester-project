package com.banking.banking_app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banking.banking_app.Entities.Employees;

import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Long> {
    Optional<Employees> findByEmployeeName(String employeeName);
}
