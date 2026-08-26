package com.example.account.service.client;
import com.example.account.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "card")
public interface CardFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<CardDto> fetchCardDetails(@RequestHeader String correlationId, @RequestParam String mobileNumber);
}
