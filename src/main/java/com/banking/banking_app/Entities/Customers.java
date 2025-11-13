package com.banking.banking_app.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name="customers")

public class Customers {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
Long CustomerId;

@Column(nullable=false)
String CustomerName;



}
