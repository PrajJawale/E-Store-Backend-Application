package com.example.eStore.transeformer;

import com.example.eStore.dto.requestDto.SellerRequestDto;
import com.example.eStore.dto.responseDto.SellerResponseDto;
import com.example.eStore.model.Seller;

public class SellertTransformer {

    public  static Seller sellerRequestDtotoSeller(SellerRequestDto sellerRequestDto){
       return Seller.builder()
                .name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .build();

    }

    public static SellerResponseDto sellerResponseDtotoSeller(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .messege("Seller Added Sucessfully")
                .build();

    }
}
