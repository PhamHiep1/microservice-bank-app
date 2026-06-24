package com.example.loan.service;

import com.example.loan.dto.LoanDto;

public interface ILoanService {
    void createLoan(String mobileNumber);
    LoanDto fetchLoan(String mobileNumber);
    boolean updateLoan(LoanDto loansDto);
    boolean deleteLoan(String mobileNumber);
}
