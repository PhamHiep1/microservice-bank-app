package com.example.account.service.impl;

import com.example.account.repository.AccountRepository;
import com.example.account.repository.CustomerRepository;
import com.example.account.service.ICustomerService;
import com.example.account.service.client.CardFeignClient;
import com.example.account.service.client.LoanFeignClient;

public class CustomerServiceImpl implements ICustomerService {
    private AccountRepository accountsRepository;
    private CustomerRepository customerRepository;

    private CardFeignClient cardFeignClient;
    private LoanFeignClient loanFeignClient;
}
