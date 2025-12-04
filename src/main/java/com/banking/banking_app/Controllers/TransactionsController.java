package com.banking.banking_app.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
///import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.banking.banking_app.Services.TransactionsService;
import com.banking.banking_app.Services.AccountsService;
import com.banking.banking_app.DTO.TransferReq;
import java.security.Principal;


@RestController
public class TransactionsController {

private final TransactionsService transactionsService;

public TransactionsController(TransactionsService transactionsService, AccountsService accountsService) {
    this.transactionsService = transactionsService;
    }

@PostMapping("/transfer")
public String transferMoney(@RequestBody TransferReq transferRequest, Principal principal) {
    
    String customerName = principal.getName();

    return "";
    



}


}
  