package com.example.eStore.controller;

import com.example.eStore.dto.requestDto.CheckOutCartRequestDto;
import com.example.eStore.dto.requestDto.ItemRequestDto;
import com.example.eStore.dto.responseDto.CardResponseDto;
import com.example.eStore.dto.responseDto.CartResponseDto;
import com.example.eStore.dto.responseDto.OrderResponseDto;
import com.example.eStore.model.Item;
import com.example.eStore.service.CartService;
import com.example.eStore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto) throws Exception {

        try{
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(),savedItem);
            return new ResponseEntity(cartResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
   public OrderResponseDto checkOutCart(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto) throws Exception {
        return cartService.checkOutCart(checkOutCartRequestDto);
   }
}
