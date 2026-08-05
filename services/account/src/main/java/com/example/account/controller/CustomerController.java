package com.example.account.controller;

import com.example.account.dto.CustomerDetailDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailDto> fetchCustomerDetails(
            @RequestParam @Pattern(regexp="(^$|[0-9]{10})", message = "Số điện thoại phải gồm 10 chữ số")
            String mobileNumber) {

        return null;
    }
}
