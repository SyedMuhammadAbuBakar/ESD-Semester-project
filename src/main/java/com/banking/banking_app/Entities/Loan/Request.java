package com.banking.banking_app.Entities.Loan;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name="requests")
public class Request {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
Long RequestId;
    
}
