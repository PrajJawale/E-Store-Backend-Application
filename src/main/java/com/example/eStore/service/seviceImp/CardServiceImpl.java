package com.example.eStore.service.seviceImp;

import com.example.eStore.Enum.UserNotFoundException;
import com.example.eStore.dto.requestDto.CardRequestDto;
import com.example.eStore.dto.responseDto.CardResponseDto;

import com.example.eStore.model.Card;
import com.example.eStore.model.Customer;

import com.example.eStore.repository.CustomerRepository;
import com.example.eStore.service.CardService;
import com.example.eStore.transeformer.CardTransFormer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws UserNotFoundException {

        Customer customer = customerRepository.findByEmailId(cardRequestDto.getEmailId());
        if(customer == null){
            throw new UserNotFoundException("User not resistered");
        }
        Card card = CardTransFormer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);
        customerRepository.save(customer);


        return CardResponseDto.builder()
                .customerName(customer.getName())
                .cardNo(card.getCardNo())
                .build();

    }
}
