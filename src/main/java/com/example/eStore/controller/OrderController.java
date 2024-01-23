package com.example.eStore.controller;

import com.example.eStore.dto.requestDto.OrderRequestDto;
import com.example.eStore.dto.responseDto.OrderResponseDto;
import com.example.eStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("order")
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/placeorder")
    public OrderResponseDto placeorder(@RequestBody OrderRequestDto orderRequestDto) throws Exception {
       return orderService.placeorder(orderRequestDto);
    }

}
