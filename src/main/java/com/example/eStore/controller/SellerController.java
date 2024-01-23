package com.example.eStore.controller;

import com.example.eStore.dto.requestDto.SellerRequestDto;
import com.example.eStore.dto.responseDto.SellerResponseDto;
import com.example.eStore.service.sellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    sellerService SellerService ;
    @PostMapping("addSeller")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) throws Exception{
      try{
          SellerResponseDto sellerResponseDto = SellerService.addSeller(sellerRequestDto);
          return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
      }catch (Exception e){
          return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
      }
    }

}
