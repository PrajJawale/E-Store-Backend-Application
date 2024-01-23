package com.example.eStore.transeformer;

import com.example.eStore.dto.requestDto.CardRequestDto;
import com.example.eStore.dto.responseDto.CardResponseDto;
import com.example.eStore.model.Card;
import com.example.eStore.model.Customer;
import com.example.eStore.repository.CardRepository;
import com.example.eStore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CardTransFormer {
    @Autowired
    static Customer customer ;
    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .expiryDate(cardRequestDto.getExpiryDate())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .build();
    }
    public static CardResponseDto CardResponseDtoCard(Card card){
        return CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .build();
    }
}
