package com.banking.banking_app.Entities;

import java.time.LocalDateTime;

import com.banking.banking_app.Enums.LoanStatus;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employees approvedBy;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Integer termMonths;

    @Enumerated(EnumType.STRING)
    private LoanStatus status = LoanStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime decisionAt;
}
