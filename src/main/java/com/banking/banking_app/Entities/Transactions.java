package com.banking.banking_app.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import lombok.AccessLevel;

@Entity
@Table(name="Transactions")
@Setter
@Getter
public class Transactions {

@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE)
@Setter(AccessLevel.NONE)
private Long transactionId;

@Column(nullable = false)
private double amount;

@ManyToOne
@JoinColumn(name="SenderId")
private Accounts SenderAccount;

@ManyToOne
@JoinColumn(name="ReceiverId")
private Accounts ReceiverAccount;

};
