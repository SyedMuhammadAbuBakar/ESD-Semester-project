package com.banking.banking_app.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanViewResponse {
    private Long loanId;
    private Double amount;
    private Integer termMonths;
    private String status;
    private String createdAt;
}
