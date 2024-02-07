package com.example.eStore.controller;

import com.example.eStore.Enum.UserNotFoundException;
import com.example.eStore.dto.requestDto.CardRequestDto;
import com.example.eStore.dto.responseDto.CardResponseDto;
import com.example.eStore.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;
    //cardService
    @PostMapping("/addcard")

        public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) throws UserNotFoundException {
         try{
             CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
             System.out.println(" check in github account");
             return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
         }catch (UserNotFoundException e){
             return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

         }

    }


    // get all VISA cards

    // get all MASTERCARD cards whose expirt is greater than 1 Jan 2025

    // Return the CardType which has maximum number of that card

}
