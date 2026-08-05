package com.example.account.service.impl;

import com.example.account.dto.AccountDto;
import com.example.account.dto.CardDto;
import com.example.account.dto.CustomerDetailsDto;
import com.example.account.dto.LoanDto;
import com.example.account.entity.Account;
import com.example.account.entity.Customer;
import com.example.account.exception.ResourceNotFoundException;
import com.example.account.mapper.AccountMapper;
import com.example.account.mapper.CustomerMapper;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.CustomerRepository;
import com.example.account.service.ICustomerService;
import com.example.account.service.client.CardFeignClient;
import com.example.account.service.client.LoanFeignClient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private final AccountRepository accountsRepository;
    private final CustomerRepository customerRepository;

    private final CardFeignClient cardFeignClient;
    private final LoanFeignClient loanFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Account account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        AccountDto accountDto = AccountMapper.mapToAccountsDto(account, new AccountDto());


        ResponseEntity<CardDto> cardDtoResponseEntity = cardFeignClient.fetchCardDetails(mobileNumber);
        ResponseEntity<LoanDto> loanDtoResponseEntity = loanFeignClient.fetchLoanDetails(mobileNumber);

        customerDetailsDto.setAccountDto(accountDto);
        customerDetailsDto.setCardDto(cardDtoResponseEntity.getBody());
        customerDetailsDto.setLoanDto(loanDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
