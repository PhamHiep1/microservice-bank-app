package com.example.loan.service.impl;

import com.example.loan.constant.LoanConstant;
import com.example.loan.dto.LoanDto;
import com.example.loan.entity.Loan;
import com.example.loan.mapper.LoanMapper;
import com.example.loan.repository.LoanRepository;
import com.example.loan.service.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanService implements ILoanService {
    private final LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> loan = loanRepository.findByMobileNumber(mobileNumber);

        if(loan.isPresent()){
            throw new IllegalArgumentException("Loan not found for mobile number: " + mobileNumber);
        }

        loanRepository.save(createNewLoan(mobileNumber));

    }

    private Loan createNewLoan(String mobileNumber) {
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        return Loan.builder()
                .mobileNumber(mobileNumber)
                .loanNumber(Long.toString(randomLoanNumber))
                .loanType(LoanConstant.HOME_LOAN)
                .totalLoan(LoanConstant.NEW_LOAN_LIMIT)
                .amountPaid(0)
                .outstandingAmount(LoanConstant.NEW_LOAN_LIMIT)
                .build();
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found for mobile number: " + mobileNumber));

        return LoanMapper.mapToLoansDto(loan, new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loansDto) {
        Optional<Loan> loan = loanRepository.findByMobileNumber(loansDto.getMobileNumber());
        if(loan.isEmpty()){
            throw new IllegalArgumentException("Loan not found for mobile number: " + loansDto.getMobileNumber());
        }
        loanRepository.save(LoanMapper.mapToLoans(loansDto, loan.get()));
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found for mobile number: " + mobileNumber));
        loanRepository.deleteById(loan.getLoanId());
        return true;
    }




}
