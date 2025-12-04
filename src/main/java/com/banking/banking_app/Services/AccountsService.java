package com.banking.banking_app.Services;

import com.banking.banking_app.Repositories.AccountsRepository;
import com.banking.banking_app.Repositories.CustomersRepository;
import com.banking.banking_app.DTO.CreateAccountReq;
import com.banking.banking_app.Entities.Accounts;
import com.banking.banking_app.Entities.Customers;
import com.banking.banking_app.Enums.AccountType;
import org.springframework.stereotype.Service;
import java.security.Principal;

@Service
public class AccountsService {

    private final AccountsRepository accountRepository;
    private final CustomersRepository customersRepository;

    public AccountsService(AccountsRepository accountRepository, CustomersRepository customersRepository) {
        this.accountRepository = accountRepository;
        this.customersRepository = customersRepository;
    }

    private String generateAccountNumber() {
    return "AC" + System.currentTimeMillis() + (int)(Math.random() * 1000);
}


public Accounts createAccount(CreateAccountReq account, Principal principal) {
    Accounts newAccount = new Accounts();
    newAccount.setAccountType(account.getAccountType());
    newAccount.setCurrentAmount(account.getInitialDeposit());

    if (account.getAccountType() == AccountType.SAVINGS) {
        newAccount.setMaxAmount(10000000);
        newAccount.setMinAmount(10000);

    } else if (account.getAccountType() == AccountType.CHECKING) {
        newAccount.setMaxAmount(500000);
        newAccount.setMinAmount(5000);

    } else {
        newAccount.setMaxAmount(1000000);
        newAccount.setMinAmount(1000);
    }

    Customers tempCustomer = customersRepository.findByCustomerName(principal.getName())
            .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

    newAccount.setCustomers(tempCustomer);

    newAccount.setAccountNo(generateAccountNumber());

    return accountRepository.save(newAccount);
}

}