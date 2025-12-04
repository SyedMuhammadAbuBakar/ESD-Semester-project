package com.banking.banking_app.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String customerName;
    private String employeeName;
    private String password;
}
