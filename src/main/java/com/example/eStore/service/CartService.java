package com.example.eStore.service;

import com.example.eStore.Enum.UserNotFoundException;
import com.example.eStore.dto.requestDto.CheckOutCartRequestDto;
import com.example.eStore.dto.responseDto.CartResponseDto;
import com.example.eStore.dto.responseDto.OrderResponseDto;
import com.example.eStore.exception.InvalidCardException;
import com.example.eStore.model.Item;

public interface CartService {
    public CartResponseDto saveCart(Integer customerId, Item item) throws Exception;
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception;
}
