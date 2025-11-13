package com.banking.banking_app.Entities.Loan;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import java.time.LocalDateTime;

@Entity
@Table(name="paymentPlan")
@Getter
@Setter
public class PaymentPlan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private Long paymentId;
    
    @Column(nullable = false)
    private Double amount;
    
    @Column(nullable = false)
    private LocalDateTime paymentDate;
    
    @Column(nullable = false)
    private String paymentMethod; // CASH, BANK_TRANSFER, CARD
    
    @Column(nullable = false)
    private String status; // PENDING, COMPLETED, FAILED
    
    // Foreign keys - uncomment when you have these entities ready
    // @ManyToOne
    // @JoinColumn(name = "loan_id")
    // private Loans loan;
    
    // @ManyToOne
    // @JoinColumn(name = "customer_id")
    // private Customers customer;
}