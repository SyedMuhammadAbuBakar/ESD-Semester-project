package com.banking.banking_app.Entities.Loan;

import org.hibernate.validator.constraints.ISBN;

import com.banking.banking_app.Entities.Customers;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
@Table(name="loanees")
@Getter
@Setter
public class Loanees {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
@Setter(AccessLevel.NONE)
private Long LoanId;

@OneToOne
@JoinColumn(name="loanee_id")
private Customers customer;


}
