package com.banking.banking_app.Entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
///import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import com.banking.banking_app.Entities.Customers;
import com.banking.banking_app.Enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Getter
@Setter
@Table(name="accounts")
public class Accounts {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
@Setter(AccessLevel.NONE)
Long AccountId;

@ManyToOne
@JoinColumn(name="customer_id")
private Customers customers;

@Enumerated(EnumType.STRING)
@Column(nullable = false)
AccountType accountType;

@Column(nullable = false)
double CurrentAmount;

@Column(nullable = false)
double MinAmount;

@Column(nullable = false)
double MaxAmount;

@Column(nullable = false, unique = true)
String AccountNo;

}
