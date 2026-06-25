package com.example.card.service.impl;
import com.example.card.constant.CardConstant;
import com.example.card.dto.CardDto;
import com.example.card.entity.Card;
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

    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCards = cardRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()){
            throw new RuntimeException("Card already exists for this mobile number");
        }
        cardRepository.save(createNewCard(mobileNumber));
    }


    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstant.CREDIT_CARD);
        newCard.setTotalLimit(CardConstant.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstant.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
        return null;
    }

    @Override
    public boolean updateCard(CardDto cardsDto) {
        return false;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        return false;
    }

}