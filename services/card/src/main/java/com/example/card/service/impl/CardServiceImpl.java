package com.example.card.service.impl;
import com.example.card.constant.CardConstant;
import com.example.card.dto.CardDto;
import com.example.card.entity.Card;
import com.example.card.exception.ResourceNotFoundException;
import com.example.card.mapper.CardMapper;
import com.example.card.repository.CardRepository;
import com.example.card.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {

    private CardRepository cardRepository;
@Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCards = cardRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()){
            throw new RuntimeException("Card already exists for this mobile number");
        }
        cardRepository.save(createNewCard(mobileNumber));
    }


    private Card createNewCard(String mobileNumber) {
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        return Card.builder().cardNumber(Long.toString(randomCardNumber))
                .mobileNumber(mobileNumber)
                .cardType(CardConstant.CREDIT_CARD)
                .totalLimit(CardConstant.NEW_CARD_LIMIT)
                .amountUsed(0)
                .availableAmount(CardConstant.NEW_CARD_LIMIT)
                .build();
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardMapper.mapToCardsDto(card, new CardDto());
    }

    @Override
    public boolean updateCard(CardDto cardsDto) {
        Card card = cardRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardMapper.mapToCards(cardsDto, card);
        cardRepository.save(card);
        return  true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardRepository.deleteById(cards.getCardId());
        return true;
    }
}