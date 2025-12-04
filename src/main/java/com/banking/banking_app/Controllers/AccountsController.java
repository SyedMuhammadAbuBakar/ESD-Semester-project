package com.banking.banking_app.Controllers;
import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.banking.banking_app.DTO.CreateAccountReq;
import com.banking.banking_app.Services.AccountsService;

@RestController
public class AccountsController {

private final AccountsService accountsService;

public AccountsController(AccountsService accountsService){
    this.accountsService=accountsService;

}

@PostMapping("/createAccount")
public String createAccount(@RequestBody CreateAccountReq createAccountReq, Principal principal) {



    accountsService.createAccount(createAccountReq, principal);


    return "";
    }

}

