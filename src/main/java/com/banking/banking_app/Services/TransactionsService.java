package com.banking.banking_app.Services;

import org.springframework.stereotype.Service;

import com.banking.banking_app.Repositories.TransactionsRepository;
import com.banking.banking_app.Entities.Transactions;
import com.banking.banking_app.Repositories.AccountsRepository;
import com.banking.banking_app.Entities.Accounts;
import jakarta.transaction.Transactional;

@Service
public class TransactionsService {

private final TransactionsRepository transactionsRepository;
private final AccountsRepository accountsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository, AccountsRepository accountsRepository) {
    this.transactionsRepository = transactionsRepository;
    this.accountsRepository = accountsRepository;

    }

    @Transactional
    public Transactions TransferMoney(Long senderAccountId, Long receiverAccountId, double amount) {

    if(accountsRepository.findById(senderAccountId).isEmpty() || accountsRepository.findById(receiverAccountId).isEmpty()) {
        throw new IllegalArgumentException("One or both accounts not found");
    }
    else if(amount <= 0) {
        throw new IllegalArgumentException("Transfer amount must be positive");
    }
    Accounts senderAccount = accountsRepository.findById(senderAccountId).get();
    Accounts receiverAccount=accountsRepository.findById(receiverAccountId).get();

    if(senderAccount.getCurrentAmount() < amount) {
        throw new IllegalArgumentException("Insufficient funds in sender's account");
    }

    double senderNewAmount = senderAccount.getCurrentAmount() - amount;
    senderAccount.setCurrentAmount(senderNewAmount);

    double receiverNewAmount=receiverAccount.getCurrentAmount()+amount;
    receiverAccount.setCurrentAmount(receiverNewAmount);

    accountsRepository.save(senderAccount);
    accountsRepository.save(receiverAccount);

    Transactions transaction = new Transactions(amount, senderAccount, receiverAccount);
    return transactionsRepository.save(transaction);
    }
}
