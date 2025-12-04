package com.banking.banking_app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.banking_app.Entities.Employees;
import com.banking.banking_app.Repositories.EmployeesRepository;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepository employeesRepository;

    // Create new employee
    public Employees createEmployee(Employees entity) {
        return employeesRepository.save(entity);
    }

    // Find employee by username
    public Employees getByEmployeeName(String name) {
        return employeesRepository.findByEmployeeName(name)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + name));
    }

    // Find employee by ID
    public Employees getEmployeeById(Long id) {
        return employeesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }
}
