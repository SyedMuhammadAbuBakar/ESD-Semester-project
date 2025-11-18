package com.banking.banking_app.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import lombok.AccessLevel;

@Entity
@Getter
@Setter
@Table(name="customers")

public class Customers {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
@Setter(AccessLevel.NONE)
Long customerId;

@Column(nullable=false)
String customerName;

@Transient
String password;

@Column(nullable=false)
String hashedPassword;

}
