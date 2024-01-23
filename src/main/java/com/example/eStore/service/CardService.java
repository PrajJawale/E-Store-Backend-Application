package com.example.eStore.service;

import com.example.eStore.Enum.UserNotFoundException;
import com.example.eStore.dto.requestDto.CardRequestDto;
import com.example.eStore.dto.responseDto.CardResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface CardService {
    public CardResponseDto addCard(@RequestBody CardRequestDto cardRequestDto) throws UserNotFoundException;
}
