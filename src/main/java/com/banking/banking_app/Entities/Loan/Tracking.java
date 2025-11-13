package com.banking.banking_app.Entities.Loan;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
@Table(name="tracking")
public class Tracking {
@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE)
@Setter(AccessLevel.NONE)
private Long loanId;
}
