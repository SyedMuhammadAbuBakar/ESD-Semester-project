package com.banking.banking_app.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.banking.banking_app.DTO.*;
import com.banking.banking_app.Entities.*;
import com.banking.banking_app.Services.*;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;
    private final CustomersService customersService;
    private final EmployeesService employeesService;

    public LoanController(LoanService loanService,
                          CustomersService customersService,
                          EmployeesService employeesService) {
        this.loanService = loanService;
        this.customersService = customersService;
        this.employeesService = employeesService;
    }

    // CUSTOMER: apply for loan
    @PostMapping("/apply")
    public ResponseEntity<?> applyForLoan(
            @RequestBody LoanApplyRequest request,
            Authentication auth) {

        Customers customer = customersService.getByCustomerName(auth.getName());

        Loan loan = new Loan();
        loan.setAmount(request.getAmount());
        loan.setTermMonths(request.getTermMonths());

        Loan created = loanService.apply(loan, customer);

        return ResponseEntity.ok("Loan application submitted with ID: " + created.getLoanId());
    }

    // CUSTOMER: view own loans
    @GetMapping("/my")
    public ResponseEntity<List<LoanViewResponse>> myLoans(Authentication auth) {

        Customers customer = customersService.getByCustomerName(auth.getName());

        List<LoanViewResponse> loans = loanService.getLoans(customer)
                .stream()
                .map(l -> {
                    LoanViewResponse dto = new LoanViewResponse();
                    dto.setLoanId(l.getLoanId());
                    dto.setAmount(l.getAmount());
                    dto.setTermMonths(l.getTermMonths());
                    dto.setStatus(l.getStatus().toString());
                    dto.setCreatedAt(l.getCreatedAt().toString());
                    return dto;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(loans);
    }

    // EMPLOYEE: approve loan
    @PostMapping("/approve/{id}")
    public ResponseEntity<LoanDecisionResponse> approveLoan(
            @PathVariable Long id,
            Authentication auth) {

        Employees employee = employeesService.getByEmployeeName(auth.getName());

        Loan loan = loanService.approve(id, employee);

        LoanDecisionResponse response = new LoanDecisionResponse();
        response.setLoanId(loan.getLoanId());
        response.setStatus(loan.getStatus().toString());
        response.setDecidedBy(employee.getEmployeeName());

        return ResponseEntity.ok(response);
    }

    // EMPLOYEE: reject loan
    @PostMapping("/reject/{id}")
    public ResponseEntity<LoanDecisionResponse> rejectLoan(
            @PathVariable Long id,
            Authentication auth) {

        Employees employee = employeesService.getByEmployeeName(auth.getName());

        Loan loan = loanService.reject(id, employee);

        LoanDecisionResponse response = new LoanDecisionResponse();
        response.setLoanId(loan.getLoanId());
        response.setStatus(loan.getStatus().toString());
        response.setDecidedBy(employee.getEmployeeName());

        return ResponseEntity.ok(response);
    }
}
