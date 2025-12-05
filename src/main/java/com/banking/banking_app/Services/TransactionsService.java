package com.banking.banking_app.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.banking.banking_app.Repositories.TransactionsRepository;
import com.banking.banking_app.Repositories.AccountsRepository;
import com.banking.banking_app.Repositories.CustomersRepository;
import com.banking.banking_app.Entities.Transactions;
import com.banking.banking_app.Entities.Accounts;
import com.banking.banking_app.Entities.Customers;
import java.util.List;

@Service
public class TransactionsService {

    private final CustomersRepository customersRepository;
    private final TransactionsRepository transactionsRepository;
    private final AccountsRepository accountsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository, 
                              AccountsRepository accountsRepository, 
                              CustomersRepository customersRepository) {
        this.transactionsRepository = transactionsRepository;
        this.accountsRepository = accountsRepository;
        this.customersRepository = customersRepository;
    }

    @Transactional
    public Transactions TransferMoney(String username, Long receiverAccountId, double amount) {
        
        // Get customer
        Customers customer = customersRepository.findByCustomerName(username)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        
        List<Accounts> senderAccounts = accountsRepository.findByCustomers(customer);
        
        if (senderAccounts.isEmpty()) {
            throw new IllegalArgumentException("You don't have any accounts");
        }
        
        Accounts senderAccount = senderAccounts.get(0);  // Use first account
        
        // Get receiver account
        Accounts receiverAccount = accountsRepository.findById(receiverAccountId)
            .orElseThrow(() -> new IllegalArgumentException("Receiver account not found"));
        
        // Validate
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        if (senderAccount.getCurrentAmount() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        
        // Transfer
        senderAccount.setCurrentAmount(senderAccount.getCurrentAmount() - amount);
        receiverAccount.setCurrentAmount(receiverAccount.getCurrentAmount() + amount);
        
        accountsRepository.save(senderAccount);
        accountsRepository.save(receiverAccount);
        
        Transactions transaction = new Transactions(amount, senderAccount, receiverAccount);
        return transactionsRepository.save(transaction);
    }
}