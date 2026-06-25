package com.example.card.service;


import com.example.card.dto.CardDto;

public interface ICardService {

    void createCard(String mobileNumber);
    CardDto fetchCard(String mobileNumber);
    boolean updateCard(CardDto cardsDto);
    boolean deleteCard(String mobileNumber);
}
