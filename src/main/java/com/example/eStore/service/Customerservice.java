package com.example.eStore.service;

import com.example.eStore.dto.requestDto.CustomerRequestDto;
import com.example.eStore.dto.responseDto.CustomerResponseDto;
import com.example.eStore.exception.EmailAlreadyResisterdException;

public interface Customerservice {

    public CustomerResponseDto addnewcustomer(CustomerRequestDto customerRequestDto) throws EmailAlreadyResisterdException;
}
