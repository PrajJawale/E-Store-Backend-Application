package com.example.eStore.transeformer;

import com.example.eStore.dto.requestDto.CustomerRequestDto;
import com.example.eStore.dto.responseDto.CustomerResponseDto;
import com.example.eStore.model.Customer;

public class CustomerTransformer {

    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmailId())
                .mobNo(customerRequestDto.getMobNo())
                .address(customerRequestDto.getAddress())
                .age(customerRequestDto.getAge())
                .build();
    }

    public static CustomerResponseDto customerResponseDtoToCustomer(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("Customer Added Successfully with =>" + customer.getEmailId())
                .build();
    }


}
