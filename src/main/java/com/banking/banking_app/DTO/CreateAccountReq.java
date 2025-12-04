package com.banking.banking_app.DTO;

import com.banking.banking_app.Enums.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountReq {
AccountType accountType;
double initialDeposit;

}
