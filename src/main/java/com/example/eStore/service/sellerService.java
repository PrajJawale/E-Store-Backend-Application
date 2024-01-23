package com.example.eStore.service;


import com.example.eStore.dto.requestDto.SellerRequestDto;
import com.example.eStore.dto.responseDto.SellerResponseDto;
import com.example.eStore.exception.SellerAlreadyPresentException;

public interface sellerService {

    public  SellerResponseDto addSeller(SellerRequestDto sellerRequestdto) throws SellerAlreadyPresentException;
}
