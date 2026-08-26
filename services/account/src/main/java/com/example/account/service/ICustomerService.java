package com.example.account.service;

import com.example.account.dto.CustomerDetailsDto;

public interface ICustomerService {
    CustomerDetailsDto fetchCustomerDetails(String phoneNumber, String correlationId);
}
