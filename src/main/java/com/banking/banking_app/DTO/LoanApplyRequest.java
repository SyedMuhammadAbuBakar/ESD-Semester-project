package com.banking.banking_app.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanApplyRequest {
    private Double amount;
    private Integer termMonths;
}
