package com.banking.banking_app.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.banking.banking_app.Entities.*;
import com.banking.banking_app.Enums.LoanStatus;
import com.banking.banking_app.Repositories.LoanRepository;

@Service
public class LoanService {

    private final LoanRepository repo;

    public LoanService(LoanRepository repo) {
        this.repo = repo;
    }


    public Loan apply(Loan loan, Customers customer) {
        loan.setCustomer(customer);
        loan.setStatus(LoanStatus.PENDING);
        return repo.save(loan);
    }


    public Loan approve(Long loanId, Employees employee) {
        Loan loan = repo.findById(loanId).orElseThrow();
        loan.setStatus(LoanStatus.APPROVED);
        loan.setApprovedBy(employee);
        loan.setDecisionAt(LocalDateTime.now());
        return repo.save(loan);
    }


    public Loan reject(Long loanId, Employees employee) {
        Loan loan = repo.findById(loanId).orElseThrow();
        loan.setStatus(LoanStatus.REJECTED);
        loan.setApprovedBy(employee);
        loan.setDecisionAt(LocalDateTime.now());
        return repo.save(loan);
    }

    public List<Loan> getLoans(Customers customer) {
        return repo.findByCustomer(customer);
    }
}
