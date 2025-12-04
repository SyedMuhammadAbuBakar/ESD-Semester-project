package com.banking.banking_app.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long employeeId;

    @Column(nullable = false, unique = true)
    private String employeeName;

    @Transient
    String password;

    @Column(nullable = false)
    private String hashedPassword;

    @Column(nullable = false)
    private String role = "EMPLOYEE";
    
}
