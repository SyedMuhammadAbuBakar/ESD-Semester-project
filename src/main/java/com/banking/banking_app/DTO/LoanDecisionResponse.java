package com.banking.banking_app.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanDecisionResponse {
    private Long loanId;
    private String status;
    private String decidedBy;
}
