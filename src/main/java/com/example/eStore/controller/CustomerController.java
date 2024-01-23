package com.example.eStore.controller;

import com.example.eStore.dto.requestDto.CustomerRequestDto;
import com.example.eStore.dto.responseDto.CustomerResponseDto;
import com.example.eStore.exception.EmailAlreadyResisterdException;
import com.example.eStore.service.Customerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    Customerservice customerservice;

    @PostMapping("/addnewcustomer")
    public CustomerResponseDto addnewcustomer(@RequestBody CustomerRequestDto customerRequestDto) throws EmailAlreadyResisterdException {
      return customerservice.addnewcustomer(customerRequestDto);
    }
}
