package com.example.eStore.service;

import com.example.eStore.dto.requestDto.OrderRequestDto;
import com.example.eStore.dto.responseDto.OrderResponseDto;
import com.example.eStore.model.Card;
import com.example.eStore.model.Customer;
import com.example.eStore.model.Orders;

public interface OrderService {
    public Orders placeOrder(Card card , Customer customer) throws Exception;
    public OrderResponseDto placeorder(OrderRequestDto orderRequestDto) throws Exception;
}
