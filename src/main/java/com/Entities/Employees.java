package com.banking.banking_app.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
@Entity
@Getter
@Setter
@Table(name="employees")
public class Employees {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
@Setter(AccessLevel.NONE)
Long EmployeeId;

}
